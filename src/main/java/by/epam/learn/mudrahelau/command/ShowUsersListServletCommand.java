package by.epam.learn.mudrahelau.command;

import by.epam.learn.mudrahelau.constant.PagesConstant;
import by.epam.learn.mudrahelau.constant.ParameterConstant;
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
 * Implementation of {@link ServletCommand}.
 * ShowUsersListServletCommand is intended for forwarding {@link User} to the page where he can
 * see a list of  {@link User}.
 * {@link User} must have {@link by.epam.learn.mudrahelau.role.Role} ADMIN.
 *
 * @see ServletCommand
 * @see CommandStorage
 */
public class ShowUsersListServletCommand implements ServletCommand {
    /**
     * @see AdminService
     */
    private AdminService adminService;
    /**
     * Constant name of the command.
     */
    private static final String COMMAND_NAME = "show_users";

    public ShowUsersListServletCommand(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user = (User) request.getSession().getAttribute(ParameterConstant.USER);
        if (user != null) {
            if (user.getRole() == Role.ADMIN) {
                List<Client> clients = adminService.retrieveClients();
                request.setAttribute(ParameterConstant.CLIENTS, clients);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(PagesConstant.USERS_LIST_PAGE);
                requestDispatcher.forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    public String getName() {
        return COMMAND_NAME;
    }
}
