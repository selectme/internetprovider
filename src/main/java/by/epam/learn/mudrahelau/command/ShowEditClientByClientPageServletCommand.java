package by.epam.learn.mudrahelau.command;

import by.epam.learn.mudrahelau.model.Client;
import by.epam.learn.mudrahelau.model.User;
import by.epam.learn.mudrahelau.service.AdminService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Viktar on 16.02.2020
 */
public class ShowEditClientByClientPageServletCommand implements ServletCommand {

    private AdminService adminService;

    public ShowEditClientByClientPageServletCommand(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String destinationPage = "index.jsp";
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            long clientId = Long.parseLong(request.getParameter("user_id"));
            if (clientId == user.getId()) {
                Client client = adminService.getClientById(clientId);
                request.setAttribute("client", client);
                destinationPage = "edit_client_by_client.jsp";
//                forwardToPage(req, resp, "edit_client_by_client.jsp");
            } RequestDispatcher requestDispatcher = request.getRequestDispatcher(destinationPage);
            requestDispatcher.forward(request, response);
        }
    }

    @Override
    public String getName() {
        return "show_edit_client_by_client_page";
    }
}
