package by.epam.learn.mudrahelau.command;

import by.epam.learn.mudrahelau.constant.PagesConstant;
import by.epam.learn.mudrahelau.constant.ParameterConstant;
import by.epam.learn.mudrahelau.model.Client;
import by.epam.learn.mudrahelau.model.User;
import by.epam.learn.mudrahelau.service.AdminService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Implementation of {@link ServletCommand}.
 * ShowEditClientByClientPageServletCommand is intended for forwarding {@link User} to the page where he can
 * change his personal data.
 * {@link User} must have {@link by.epam.learn.mudrahelau.role.Role} CLIENT.
 *
 * @see ServletCommand
 * @see CommandStorage
 */
public class ShowEditClientByClientPageServletCommand implements ServletCommand {
    /**
     * @see AdminService
     */
    private AdminService adminService;
    /**
     * Constant name of the command.
     */
    private static final String COMMAND_NAME = "show_edit_client_by_client_page";

    public ShowEditClientByClientPageServletCommand(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String destinationPage = PagesConstant.MAIN_PAGE;
        User user = (User) request.getSession().getAttribute(ParameterConstant.USER);
        if (user != null) {
            long clientId = Long.parseLong(request.getParameter(ParameterConstant.USER_ID));
            if (clientId == user.getId()) {
                Client client = adminService.getClientById(clientId);
                request.setAttribute(ParameterConstant.CLIENT, client);
                destinationPage = PagesConstant.EDIT_BY_CLIENT_PAGE;
            }
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(destinationPage);
            requestDispatcher.forward(request, response);
        }
    }

    @Override
    public String getName() {
        return COMMAND_NAME;
    }
}
