package by.epam.learn.mudrahelau.command;

import by.epam.learn.mudrahelau.model.Payment;
import by.epam.learn.mudrahelau.model.User;
import by.epam.learn.mudrahelau.payment.PaymentType;
import by.epam.learn.mudrahelau.service.AdminService;
import by.epam.learn.mudrahelau.service.ClientService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Viktar on 16.02.2020
 */
public class ChangeTariffPlanServletCommand implements ServletCommand {

    private AdminService adminService;
    private ClientService clientService;

    public ChangeTariffPlanServletCommand(AdminService adminService, ClientService clientService) {
        this.adminService = adminService;
        this.clientService = clientService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            long clientId = Long.parseLong(request.getParameter("user_id"));
            int tariffId = Integer.parseInt(request.getParameter("tariff_id"));
            BigDecimal clientMoney = clientService.retrieveClientMoneyAmountByClientId(clientId);
            BigDecimal tariffPlanPrice = adminService.getTariffPlanById(tariffId).getPrice();
            if (clientMoney != null && clientMoney.compareTo(tariffPlanPrice) >= 0) {
                Payment payment = new Payment(clientId, tariffPlanPrice.negate(), PaymentType.DEBIT, LocalDateTime.now());
                adminService.makePaymentAndChangeTariff(clientId, tariffId, payment);
                response.sendRedirect("do?action=show_client_account_page&user_id=" + user.getId());
            } else {
                response.sendRedirect("do?action=show_client_account_page&user_id=" + user.getId());
            }
        }
    }

    @Override
    public String getName() {
        return "change_tariff_plan";
    }
}
