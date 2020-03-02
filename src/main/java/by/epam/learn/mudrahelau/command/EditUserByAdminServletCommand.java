package by.epam.learn.mudrahelau.command;

import by.epam.learn.mudrahelau.constant.LoggerConstants;
import by.epam.learn.mudrahelau.constant.PagesConstant;
import by.epam.learn.mudrahelau.constant.ParameterConstant;
import by.epam.learn.mudrahelau.constant.RedirectConstants;
import by.epam.learn.mudrahelau.model.Client;
import by.epam.learn.mudrahelau.model.Payment;
import by.epam.learn.mudrahelau.model.TariffPlan;
import by.epam.learn.mudrahelau.model.User;
import by.epam.learn.mudrahelau.payment.PaymentType;
import by.epam.learn.mudrahelau.service.AdminService;
import by.epam.learn.mudrahelau.status.ClientStatus;
import by.epam.learn.mudrahelau.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static by.epam.learn.mudrahelau.validator.AdminValidator.checkUserIsAdmin;

/**
 * Implementation of {@link ServletCommand}.
 * EditUserByAdminServletCommand is intended to edit {@link User}.
 *
 * @see ServletCommand
 * @see CommandStorage
 */
public class EditUserByAdminServletCommand implements ServletCommand {
    /**
     * @see AdminService
     */
    private AdminService adminService;
    /**
     * Constant name of the command.
     */
    private static final String COMMAND_NAME = "edit_user_by_admin";
    /**
     * Localized error message.
     */
    private static final String MESSAGE = "label.edit.by.client.error";

    private static final Logger logger = LogManager.getLogger(EditUserByAdminServletCommand.class);

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
                String name = request.getParameter(ParameterConstant.NAME).trim();
                String surname = request.getParameter(ParameterConstant.SURNAME).trim();
                int tariffPlanId;
                try {
                    tariffPlanId = Integer.parseInt(request.getParameter(ParameterConstant.TARIFF_ID));
                } catch (NumberFormatException e) {
                    logger.error(LoggerConstants.NUMBER_FORMAT_EXCEPTION, e);
                    tariffPlanId = ParameterConstant.FAKE_TARIFF_ID;
                }
                ClientStatus status = ClientStatus.valueOf(request.getParameter(ParameterConstant.STATUS).toUpperCase());
                Client client = new Client();
                client.setId(clientId);
                client.setLogin(login);
                client.setName(name);
                client.setSurname(surname);
                client.setStatus(status);
                client.setTariffPlan(adminService.getTariffPlanByClientId(clientId));
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
                    List<TariffPlan> tariffPlans = adminService.retrieveTariffPlans();
                    request.setAttribute(ParameterConstant.ERROR_ATTRIBUTE, MESSAGE);
                    request.setAttribute(ParameterConstant.TARIFF_PLANS, tariffPlans);
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
