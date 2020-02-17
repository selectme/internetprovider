package by.epam.learn.mudrahelau.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Viktar on 16.02.2020
 */
public class NotExistsServletCommand implements ServletCommand{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.sendRedirect("/");
    }

    @Override
    public String getName() {
        return "not_exists";
    }
}
