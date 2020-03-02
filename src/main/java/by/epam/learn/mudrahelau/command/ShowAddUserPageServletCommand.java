package by.epam.learn.mudrahelau.command;

import by.epam.learn.mudrahelau.constant.PagesConstant;
import by.epam.learn.mudrahelau.constant.ParameterConstant;
import by.epam.learn.mudrahelau.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.learn.mudrahelau.validator.AdminValidator.checkUserIsAdmin;

/**
 * Implementation of {@link ServletCommand}.
 * ShowAddUserPageServletCommand is intended for forwarding {@link User} to the page where he can add the new {@link User}
 * into the database.
 * {@link User} must have {@link by.epam.learn.mudrahelau.role.Role} ADMIN.
 *
 * @see ServletCommand
 * @see CommandStorage
 */
public class ShowAddUserPageServletCommand implements ServletCommand {
    /**
     * Constant name of the command.
     */
    private static final String COMMAND_NAME = "show_add_client_page";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user = (User) request.getSession().getAttribute(ParameterConstant.USER);
        String destinationPage = PagesConstant.MAIN_PAGE;
        if (checkUserIsAdmin(user)) {
            destinationPage = PagesConstant.ADD_USER_PAGE;
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(destinationPage);
        requestDispatcher.forward(request, response);
    }

    @Override
    public String getName() {
        return COMMAND_NAME;
    }
}
