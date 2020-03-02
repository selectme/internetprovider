package by.epam.learn.mudrahelau.command;

import by.epam.learn.mudrahelau.constant.PagesConstant;
import by.epam.learn.mudrahelau.constant.ParameterConstant;
import by.epam.learn.mudrahelau.constant.RedirectConstants;
import by.epam.learn.mudrahelau.model.TariffPlan;
import by.epam.learn.mudrahelau.model.User;
import by.epam.learn.mudrahelau.service.AdminService;
import by.epam.learn.mudrahelau.validator.AdminValidator;
import by.epam.learn.mudrahelau.validator.TariffValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;


/**
 * Implementation of {@link ServletCommand}.
 * AddTariffServletCommand is intended to add {@link TariffPlan} into a database.
 *
 * @see ServletCommand
 * @see CommandStorage
 */
public class AddTariffServletCommand implements ServletCommand {

    /**
     * @see AdminService
     */
    private AdminService adminService;
    /**
     * Constant name of the command.
     */
    private static final String COMMAND_NAME = "add_tariff";
    /**
     * Localized error message.
     */
    private static final String ERROR_MESSAGE = "label.incorrect.tariff.data.error";


    public AddTariffServletCommand(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String destinationPage = PagesConstant.MAIN_PAGE;
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(destinationPage);
        User user = (User) request.getSession().getAttribute(ParameterConstant.USER);
        if (user != null) {

            if (AdminValidator.checkUserIsAdmin(user)) {
                String title = request.getParameter(ParameterConstant.TITLE);
                int speed = Integer.parseInt(request.getParameter(ParameterConstant.SPEED));
                BigDecimal price = new BigDecimal(request.getParameter(ParameterConstant.PRICE));
                TariffPlan tariffPlan = new TariffPlan();
                tariffPlan.setTitle(title);
                tariffPlan.setSpeed(speed);
                tariffPlan.setPrice(price);
                if (TariffValidator.validateCreatingTariff(tariffPlan)) {
                    adminService.addTariffPlan(tariffPlan);
                    response.sendRedirect(RedirectConstants.SHOW_TARIFFS_REDIRECT);
                } else {
                    request.setAttribute(ParameterConstant.ERROR_ATTRIBUTE, ERROR_MESSAGE);
                    destinationPage = PagesConstant.ADD_TARIFF_PAGE;
                    request.getRequestDispatcher(destinationPage).forward(request, response);
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
