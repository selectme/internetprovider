package by.epam.learn.mudrahelau.command;

import by.epam.learn.mudrahelau.constant.RedirectConstants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Implementation of {@link ServletCommand}.
 * NotExistsServletCommand is intended for cases when {@link by.epam.learn.mudrahelau.model.User}
 * enters the wrong command. In this case he will be redirected to the main page.
 *
 * @see ServletCommand
 * @see CommandStorage
 */
public class NotExistsServletCommand implements ServletCommand{
    /**
     * Constant name of the command.
     */
    private static final String COMMAND_NAME = "not_exists";
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.sendRedirect(RedirectConstants.MAIN_PAGE_REDIRECT);
    }

    @Override
    public String getName() {
        return COMMAND_NAME;
    }
}
