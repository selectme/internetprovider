package by.epam.learn.mudrahelau.command;

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

    public EditClientByClientServletCommand(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            long clientId = Long.parseLong(request.getParameter("user_id"));
            if (clientId == user.getId()) {
                Client client = new Client();
                String name = request.getParameter("name");
                String surname = request.getParameter("surname");
                client.setId(clientId);
                client.setName(name);
                client.setSurname(surname);
                clientService.editClientByClient(client);
                response.sendRedirect("do?action=show_client_account_page&user_id=" + user.getId());
            } else {
                requestDispatcher.forward(request,response);
            }
        } else {
            requestDispatcher.forward(request,response);
        }
    }

    @Override
    public String getName() {
        return "edit_client_by_client";
    }
}
