package by.epam.learn.mudrahelau.command;

import by.epam.learn.mudrahelau.constant.ParameterConstant;
import by.epam.learn.mudrahelau.model.User;
import by.epam.learn.mudrahelau.role.Role;
import by.epam.learn.mudrahelau.service.AdminService;
import by.epam.learn.mudrahelau.validator.AdminValidator;
import by.epam.learn.mudrahelau.validator.LoginValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Viktar on 16.02.2020
 */
public class AddUserServletCommand implements ServletCommand {
    private AdminService adminService;
    private static final String COMMAND_NAME = "add_user";

    public AddUserServletCommand(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user = (User) request.getSession().getAttribute(ParameterConstant.USER);
        if (user != null) {
            if (AdminValidator.checkUserIsAdmin(user)) {
                String login = request.getParameter(ParameterConstant.LOGIN);
                String password = request.getParameter(ParameterConstant.PASSWORD);
                String name = request.getParameter(ParameterConstant.NAME);
                String surname = request.getParameter(ParameterConstant.SURNAME);
                Role role = Role.valueOf(request.getParameter(ParameterConstant.ROLE));
                if (LoginValidator.checkLoginIsUnique(login)) {
                    adminService.createUser(login, password, name, surname, role);
                    response.sendRedirect("/do?action=show_users");
                } else {
                    response.sendRedirect("/do?action=show_add_client_page_error");
                }
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } else {
            response.sendRedirect("/do?action=show_login_page");
        }
    }

    @Override
    public String getName() {
        return COMMAND_NAME;
    }
}
