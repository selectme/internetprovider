package by.epam.learn.mudrahelau.command;

import by.epam.learn.mudrahelau.constant.PagesConstant;
import by.epam.learn.mudrahelau.constant.ParameterConstant;
import by.epam.learn.mudrahelau.model.Client;
import by.epam.learn.mudrahelau.model.User;
import by.epam.learn.mudrahelau.service.ClientService;

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
                if ((!client.getName().trim().equals("")) && (!client.getSurname().trim().equals(""))) {
                    clientService.editClientByClient(client);
                    response.sendRedirect("do?action=show_client_account_page&user_id=" + user.getId());
                } else {
                    request.setAttribute("client", client);
                    request.setAttribute("error", "label.empty.error");
                    request.getRequestDispatcher(PagesConstant.EDIT_BY_CLIENT_PAGE).forward(request,response);
//                    response.sendRedirect("do?action=show_edit_client_by_client_page&user_id=" + user.getId());
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
