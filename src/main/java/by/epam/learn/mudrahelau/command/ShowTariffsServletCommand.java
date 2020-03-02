package by.epam.learn.mudrahelau.command;

import by.epam.learn.mudrahelau.constant.PagesConstant;
import by.epam.learn.mudrahelau.constant.ParameterConstant;
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
 * ShowTariffsServletCommand is intended for forwarding {@link User} to the page where he can
 * see list of  {@link TariffPlan}.
 *
 * @see ServletCommand
 * @see CommandStorage
 */
public class ShowTariffsServletCommand implements ServletCommand {
    /**
     * @see AdminService
     */
    private AdminService adminService;
    /**
     * Constant name of the command.
     */
    private static final String COMMAND_NAME = "show_tariffs";

    public ShowTariffsServletCommand(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<TariffPlan> tariffPlans = adminService.retrieveTariffPlans();
        request.setAttribute(ParameterConstant.TARIFF_PLANS, tariffPlans);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(PagesConstant.TARIFF_PLANS_PAGE);
        requestDispatcher.forward(request, response);
    }

    @Override
    public String getName() {
        return COMMAND_NAME;
    }
}
