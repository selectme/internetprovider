package by.epam.learn.mudrahelau.command;

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

    public AddUserServletCommand(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            if (AdminValidator.checkUserIsAdmin(user)) {
                String login = request.getParameter("login");
                String password = request.getParameter("password");
                String name = request.getParameter("name");
                String surname = request.getParameter("surname");
                Role role = Role.valueOf(request.getParameter("role"));
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
        return "add_user";
    }
}
