package by.epam.learn.mudrahelau.command;

import by.epam.learn.mudrahelau.model.Client;
import by.epam.learn.mudrahelau.model.Payment;
import by.epam.learn.mudrahelau.model.User;
import by.epam.learn.mudrahelau.payment.PaymentType;
import by.epam.learn.mudrahelau.service.AdminService;
import by.epam.learn.mudrahelau.status.ClientStatus;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static by.epam.learn.mudrahelau.validator.AdminValidator.checkUserIsAdmin;

/**
 * @author Viktar on 16.02.2020
 */
public class EditUserByAdminServletCommand implements ServletCommand {

    private AdminService adminService;

    public EditUserByAdminServletCommand(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            if (checkUserIsAdmin(user)) {
                long clientId = Long.parseLong(request.getParameter("user_id"));
                String login = request.getParameter("login");
                String name = request.getParameter("name");
                String surname = request.getParameter("surname");
                int tariffPlanId;
                try {
                    tariffPlanId = Integer.parseInt(request.getParameter("tariff_id"));
                } catch (NumberFormatException e) {
                    tariffPlanId = 0;
                }
                ClientStatus status = ClientStatus.valueOf(request.getParameter("status").toUpperCase());
                Client client = new Client();
                client.setId(clientId);
                client.setLogin(login);
                client.setName(name);
                client.setSurname(surname);
                client.setStatus(status);
                adminService.editClientByAdmin(client);
                if (status == ClientStatus.INACTIVE) {
                    adminService.makeInactiveClient(clientId);
                } else if (tariffPlanId != adminService.getTariffPlanByClientId(clientId).getId()) {
                    if (tariffPlanId != 0) {
                        Payment payment = new Payment(clientId, new BigDecimal(0), PaymentType.DEBIT, LocalDateTime.now());
                        adminService.makePaymentAndChangeTariff(clientId, tariffPlanId, payment);
                    }
                }
                response.sendRedirect("/do?action=show_users");
            } else {
                requestDispatcher.forward(request, response);
            }
        } else {
            requestDispatcher.forward(request, response);
        }
    }

    @Override
    public String getName() {
        return "edit_user_by_admin";
    }
}
