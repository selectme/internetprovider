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
public class ShowPaymentPageServletCommand implements ServletCommand {

    private AdminService adminService;

    public ShowPaymentPageServletCommand(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String destinationPage = "index.jsp";
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            long client_id = Long.parseLong(request.getParameter("user_id"));
            if (client_id == user.getId()) {
                Client client = adminService.getClientById(client_id);
                request.setAttribute("client", client);
                destinationPage = "make_payment_page.jsp";
//                forwardToPage(req, resp, "make_payment_page.jsp");
            }
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(destinationPage);
            requestDispatcher.forward(request, response);
        }
    }

    @Override
    public String getName() {
        return "show_payment_page";
    }
}
