package by.epam.learn.mudrahelau.command;

import by.epam.learn.mudrahelau.constant.PagesConstant;
import by.epam.learn.mudrahelau.constant.ParameterConstant;
import by.epam.learn.mudrahelau.model.User;
import by.epam.learn.mudrahelau.service.AdminService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.learn.mudrahelau.validator.AdminValidator.checkUserIsAdmin;

/**
 * @author Viktar on 16.02.2020
 */
public class DeleteTariffPlanServletCommand implements ServletCommand {

    private AdminService adminService;
    private static final String COMMAND_NAME = "delete_tariff_plan";

    public DeleteTariffPlanServletCommand(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user = (User) request.getSession().getAttribute(ParameterConstant.USER);
        if (user != null) {
            if (checkUserIsAdmin(user)) {
                int tariff_id = Integer.parseInt(request.getParameter(ParameterConstant.TARIFF_ID));
                adminService.deleteTariffPlanById(tariff_id);
                response.sendRedirect("/do?action=show_tariffs");
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } else {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(PagesConstant.MAIN_PAGE);
            requestDispatcher.forward(request,response);
        }
    }

    @Override
    public String getName() {
        return COMMAND_NAME;
    }
}
