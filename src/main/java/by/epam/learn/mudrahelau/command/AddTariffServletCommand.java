package by.epam.learn.mudrahelau.command;

import by.epam.learn.mudrahelau.model.User;
import by.epam.learn.mudrahelau.service.AdminService;
import by.epam.learn.mudrahelau.service.UserService;
import by.epam.learn.mudrahelau.validator.AdminValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * @author Viktar on 16.02.2020
 */
public class AddTariffServletCommand implements ServletCommand {

    private AdminService adminService;


    public AddTariffServletCommand(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {

            if (AdminValidator.checkUserIsAdmin(user)) {
                String title = request.getParameter("title");
                int speed = Integer.parseInt(request.getParameter("speed"));
                BigDecimal price = new BigDecimal(request.getParameter("price"));
                adminService.createTariffPlan(title, speed, price);
                response.sendRedirect("/do?action=show_tariffs");
            } else {
                requestDispatcher.forward(request, response);
            }
        } else {
            requestDispatcher.forward(request, response);
        }
    }

    @Override
    public String getName() {
        return "add_tariff";
    }
}
