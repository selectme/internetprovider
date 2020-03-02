package by.epam.learn.mudrahelau.command;

import by.epam.learn.mudrahelau.constant.PagesConstant;
import by.epam.learn.mudrahelau.constant.ParameterConstant;
import by.epam.learn.mudrahelau.model.Client;
import by.epam.learn.mudrahelau.model.TariffPlan;
import by.epam.learn.mudrahelau.model.User;
import by.epam.learn.mudrahelau.service.AdminService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Implementation of {@link ServletCommand}.
 * ShowChangeTariffServletCommand is intended for forwarding {@link User} to the page where he can change {@link TariffPlan}.
 *
 * @see ServletCommand
 * @see CommandStorage
 */
public class ShowChangeTariffServletCommand implements ServletCommand {
    /**
     * @see AdminService
     */
    private AdminService adminService;
    /**
     * Constant name of the command.
     */
    private static final String COMMAND_NAME = "show_change_tariff_page";

    public ShowChangeTariffServletCommand(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String destinationPage = PagesConstant.MAIN_PAGE;
        User user = (User) request.getSession().getAttribute(ParameterConstant.USER);
        if (user != null) {
            long clientId = Long.parseLong(request.getParameter(ParameterConstant.USER_ID));
            if (clientId == user.getId()) {
                Client client = adminService.getClientById(clientId);
                TariffPlan tariff = adminService.getTariffPlanByClientId(clientId);
                List<TariffPlan> tariffPlans = adminService.retrieveTariffPlans();
                request.setAttribute(ParameterConstant.CLIENT, client);
                request.setAttribute(ParameterConstant.TARIFF, tariff);
                request.setAttribute(ParameterConstant.TARIFF_PLANS, tariffPlans);
                destinationPage = PagesConstant.CHANGE_TARIFF_PAGE;
            }
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(destinationPage);
            requestDispatcher.forward(request, response);
        }
    }

    @Override
    public String getName() {
        return COMMAND_NAME;
    }
}
