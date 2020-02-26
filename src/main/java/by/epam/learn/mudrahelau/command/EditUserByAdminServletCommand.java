package by.epam.learn.mudrahelau.command;

import by.epam.learn.mudrahelau.constant.PagesConstant;
import by.epam.learn.mudrahelau.constant.ParameterConstant;
import by.epam.learn.mudrahelau.constant.RedirectConstants;
import by.epam.learn.mudrahelau.model.Client;
import by.epam.learn.mudrahelau.model.Payment;
import by.epam.learn.mudrahelau.model.User;
import by.epam.learn.mudrahelau.payment.PaymentType;
import by.epam.learn.mudrahelau.service.AdminService;
import by.epam.learn.mudrahelau.status.ClientStatus;
import by.epam.learn.mudrahelau.validator.UserValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

import static by.epam.learn.mudrahelau.validator.AdminValidator.checkUserIsAdmin;

/**
 * @author Viktar on 16.02.2020
 */
public class EditUserByAdminServletCommand implements ServletCommand {

    private static final String COMMAND_NAME = "edit_user_by_admin";
    private static final String MESSAGE = "label.edit.by.client.error";
    private AdminService adminService;

    public EditUserByAdminServletCommand(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(PagesConstant.MAIN_PAGE);
        User user = (User) request.getSession().getAttribute(ParameterConstant.USER);
        if (user != null) {
            if (checkUserIsAdmin(user)) {
                long clientId = Long.parseLong(request.getParameter(ParameterConstant.USER_ID));
                String login = request.getParameter(ParameterConstant.LOGIN);
                String name = request.getParameter(ParameterConstant.NAME);
                String surname = request.getParameter(ParameterConstant.SURNAME);
                int tariffPlanId;
                try {
                    tariffPlanId = Integer.parseInt(request.getParameter(ParameterConstant.TARIFF_ID));
                } catch (NumberFormatException e) {
                    tariffPlanId = ParameterConstant.FAKE_TARIFF_ID;
                }
                ClientStatus status = ClientStatus.valueOf(request.getParameter(ParameterConstant.STATUS).toUpperCase());
                Client client = new Client();
                client.setId(clientId);
                client.setLogin(login);
                client.setName(name);
                client.setSurname(surname);
                client.setStatus(status);
                if (UserValidator.validateEditingClient(client)) {
                    adminService.editClientByAdmin(client);
                    if (status == ClientStatus.INACTIVE) {
                        adminService.makeInactiveClient(clientId);
                    } else if (tariffPlanId != adminService.getTariffPlanByClientId(clientId).getId()) {
                        if (tariffPlanId != ParameterConstant.FAKE_TARIFF_ID) {
                            Payment payment = new Payment(clientId, ParameterConstant.ZERO_PAYMENT, PaymentType.DEBIT, LocalDateTime.now());
                            adminService.makePaymentAndChangeTariff(clientId, tariffPlanId, payment);
                        }
                    }
                    response.sendRedirect(RedirectConstants.SHOW_USERS_REDIRECT);
                } else {
                    request.setAttribute(ParameterConstant.CLIENT, client);
                    request.setAttribute(ParameterConstant.ERROR_ATTRIBUTE, MESSAGE);
                    request.getRequestDispatcher(PagesConstant.EDIT_BY_ADMIN_PAGE).forward(request, response);
                }
            } else {
                requestDispatcher.forward(request, response);
            }
        } else {
            requestDispatcher.forward(request, response);
        }
    }

    @Override
    public String getName() {
        return COMMAND_NAME;
    }
}
