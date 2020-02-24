package by.epam.learn.mudrahelau.command;

import by.epam.learn.mudrahelau.constant.RedirectConstants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Viktar on 16.02.2020
 */
public class NotExistsServletCommand implements ServletCommand{
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
