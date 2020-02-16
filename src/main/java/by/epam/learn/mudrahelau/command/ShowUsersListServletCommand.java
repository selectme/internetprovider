package by.epam.learn.mudrahelau.command;

import by.epam.learn.mudrahelau.model.Client;
import by.epam.learn.mudrahelau.model.User;
import by.epam.learn.mudrahelau.role.Role;
import by.epam.learn.mudrahelau.service.AdminService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Viktar on 16.02.2020
 */
public class ShowUsersListServletCommand implements ServletCommand {

    private AdminService adminService;

    public ShowUsersListServletCommand(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            if (user.getRole() == Role.ADMIN) {
                List<Client> clients = adminService.retrieveClients();
                request.setAttribute("clients", clients);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("users_list.jsp");
                requestDispatcher.forward(request, response);
//                forwardToPage(req, resp, "users_list.jsp");
            } else {
//                forwardToPage(req, resp, "index.jsp");
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } else {
//            forwardToPage(req, resp, "index.jsp");
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    public String getName() {
        return "show_users";
    }
}
