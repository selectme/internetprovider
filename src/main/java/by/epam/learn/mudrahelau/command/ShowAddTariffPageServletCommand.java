package by.epam.learn.mudrahelau.command;

import by.epam.learn.mudrahelau.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.learn.mudrahelau.validator.AdminValidator.checkUserIsAdmin;

/**
 * @author Viktar on 16.02.2020
 */
public class ShowAddTariffPageServletCommand implements ServletCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String destinationPage = "index.jsp";
        User user = (User) request.getSession().getAttribute("user");
        if (checkUserIsAdmin(user)) {
            destinationPage = "add_tariff_plan.jsp";
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(destinationPage);
        requestDispatcher.forward(request, response);
    }

    @Override
    public String getName() {
        return "show_add_tariff_page";
    }
}
