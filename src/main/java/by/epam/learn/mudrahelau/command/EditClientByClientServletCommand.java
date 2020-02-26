package by.epam.learn.mudrahelau.command;

import by.epam.learn.mudrahelau.constant.PagesConstant;
import by.epam.learn.mudrahelau.constant.ParameterConstant;
import by.epam.learn.mudrahelau.constant.RedirectConstants;
import by.epam.learn.mudrahelau.model.Client;
import by.epam.learn.mudrahelau.model.User;
import by.epam.learn.mudrahelau.service.ClientService;
import by.epam.learn.mudrahelau.validator.UserValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Viktar on 16.02.2020
 */
public class EditClientByClientServletCommand implements ServletCommand {

    private ClientService clientService;
    private static final String COMMAND_NAME = "edit_client_by_client";
    private static final String MESSAGE = "label.edit.by.client.error";


    public EditClientByClientServletCommand(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(PagesConstant.MAIN_PAGE);
        User user = (User) request.getSession().getAttribute(ParameterConstant.USER);
        if (user != null) {
            long clientId = Long.parseLong(request.getParameter(ParameterConstant.USER_ID));
            if (clientId == user.getId()) {
                Client client = new Client();
                String name = request.getParameter(ParameterConstant.NAME);
                String surname = request.getParameter(ParameterConstant.SURNAME);
                client.setId(clientId);
                client.setName(name);
                client.setSurname(surname);
                if (UserValidator.validateEditingClient(client)) {
                    clientService.editClientByClient(client);
                    response.sendRedirect(RedirectConstants.CLIENT_ACCOUNT_REDIRECT + user.getId());
                } else {
                    request.setAttribute(ParameterConstant.CLIENT, client);
                    request.setAttribute(ParameterConstant.ERROR_ATTRIBUTE, MESSAGE);
                    request.getRequestDispatcher(PagesConstant.EDIT_BY_CLIENT_PAGE).forward(request, response);
                }
            } else {
                requestDispatcher.forward(request, response);
            }
        } else {
            requestDispatcher.forward(request, response);
        }
    }

    @Override
    public String getName() {
        return COMMAND_NAME;
    }
}
