package by.epam.learn.mudrahelau.command;

import by.epam.learn.mudrahelau.constant.PagesConstant;
import by.epam.learn.mudrahelau.constant.ParameterConstant;
import by.epam.learn.mudrahelau.constant.RedirectConstants;
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
public class DeleteUserServletCommand implements ServletCommand {

    private AdminService adminService;
    private static final String COMMAND_NAME = "delete_user";

    public DeleteUserServletCommand(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(PagesConstant.MAIN_PAGE);
        User user = (User) request.getSession().getAttribute(ParameterConstant.USER);
        if (user != null) {
            if (checkUserIsAdmin(user)) {
                long userId = Long.parseLong(request.getParameter(ParameterConstant.USER_ID));
                adminService.deleteUserById(userId);
                response.sendRedirect(RedirectConstants.SHOW_USERS_REDIRECT);
            } else {
                requestDispatcher.forward(request,response);
            }
        } else {
            requestDispatcher.forward(request,response);
        }
    }

    @Override
    public String getName() {
        return COMMAND_NAME;
    }
}
