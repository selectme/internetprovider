package by.epam.learn.mudrahelau.command;

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

    public DeleteUserServletCommand(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            if (checkUserIsAdmin(user)) {
                long userId = Long.parseLong(request.getParameter("user_id"));
                adminService.deleteUserById(userId);
                response.sendRedirect("/do?action=show_users");
            } else {
                requestDispatcher.forward(request,response);
            }
        } else {
            requestDispatcher.forward(request,response);
        }
    }

    @Override
    public String getName() {
        return "delete_user";
    }
}
