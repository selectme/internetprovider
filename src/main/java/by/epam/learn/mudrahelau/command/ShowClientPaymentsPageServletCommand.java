package by.epam.learn.mudrahelau.command;


import by.epam.learn.mudrahelau.constant.PagesConstant;
import by.epam.learn.mudrahelau.constant.ParameterConstant;
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
 * Implementation of {@link ServletCommand}.
 * ShowClientPaymentsPageServletCommand is intended for forwarding {@link User} to the page where he can see his
 * {@link Payment} history.
 * {@link User} must have {@link by.epam.learn.mudrahelau.role.Role} CLIENT.
 *
 * @see ServletCommand
 * @see CommandStorage
 */
public class ShowClientPaymentsPageServletCommand implements ServletCommand {
    /**
     * @see ClientService
     */
    private ClientService clientService;
    /**
     * Constant name of the command.
     */
    private static final String COMMAND_NAME = "show_clients_payments_page";

    public ShowClientPaymentsPageServletCommand(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String destinationPage = PagesConstant.MAIN_PAGE;
        User user = (User) request.getSession().getAttribute(ParameterConstant.USER);
        if (user != null) {
            List<Payment> payments = clientService.retrievePayments(user.getId());
            request.setAttribute(ParameterConstant.PAYMENTS, payments);
            destinationPage = PagesConstant.PAYMENTS_PAGE;
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(destinationPage);
        requestDispatcher.forward(request, response);
    }

    @Override
    public String getName() {
        return COMMAND_NAME;
    }
}
