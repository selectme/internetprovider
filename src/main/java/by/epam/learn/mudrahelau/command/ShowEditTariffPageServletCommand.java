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

import static by.epam.learn.mudrahelau.validator.AdminValidator.checkUserIsAdmin;

/**
 * Implementation of {@link ServletCommand}.
 * ShowEditTariffPageServletCommand is intended for forwarding {@link User} to the page where he can
 * edit {@link TariffPlan}.
 * {@link User} must have {@link by.epam.learn.mudrahelau.role.Role} ADMIN.
 *
 * @see ServletCommand
 * @see CommandStorage
 */
public class ShowEditTariffPageServletCommand implements ServletCommand {
    /**
     * @see AdminService
     */
    private AdminService adminService;
    /**
     * Constant name of the command.
     */
    private static final String COMMAND_NAME = "show_edit_tariffplan_page";

    public ShowEditTariffPageServletCommand(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String destinationPage = PagesConstant.MAIN_PAGE;
        User user = (User) request.getSession().getAttribute(ParameterConstant.USER);
        if (checkUserIsAdmin(user)) {
            int id = Integer.parseInt(request.getParameter(ParameterConstant.TARIFF_ID));
            TariffPlan tariffPlan = adminService.getTariffPlanById(id);
            request.setAttribute(ParameterConstant.TARIFF, tariffPlan);
            destinationPage = PagesConstant.EDIT_TARIFF_PAGE;
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(destinationPage);
        requestDispatcher.forward(request, response);
    }

    @Override
    public String getName() {
        return COMMAND_NAME;
    }
}
