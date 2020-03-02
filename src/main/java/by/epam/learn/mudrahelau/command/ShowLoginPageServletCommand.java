package by.epam.learn.mudrahelau.command;

import by.epam.learn.mudrahelau.constant.PagesConstant;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Implementation of {@link ServletCommand}.
 * ShowLoginPageServletCommand is intended for forwarding to the page where {@link by.epam.learn.mudrahelau.model.User}
 * can log in in his account.
 *
 * @see ServletCommand
 * @see CommandStorage
 */
public class ShowLoginPageServletCommand implements ServletCommand {
    /**
     * Constant name of the command.
     */
    private static final String COMMAND_NAME = "show_login_page";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(PagesConstant.LOGIN_PAGE);
        dispatcher.forward(request, response);
    }

    @Override
    public String getName() {
        return COMMAND_NAME;
    }
}
