package by.epam.learn.mudrahelau.command;

import by.epam.learn.mudrahelau.model.Payment;
import by.epam.learn.mudrahelau.model.User;
import by.epam.learn.mudrahelau.service.ClientService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Viktar on 16.02.2020
 */
public class ShowClientPaymentsPageServletCommand implements ServletCommand {

    private ClientService clientService;

    public ShowClientPaymentsPageServletCommand(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String destinationPage = "index.jsp";
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            List<Payment> payments = clientService.retrievePayments(user.getId());
            request.setAttribute("payments", payments);
            destinationPage = "payments_page.jsp";
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(destinationPage);
        requestDispatcher.forward(request, response);
    }

    @Override
    public String getName() {
        return "show_clients_payments_page";
    }
}
