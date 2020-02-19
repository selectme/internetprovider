package by.epam.learn.mudrahelau.command;

import by.epam.learn.mudrahelau.constant.PagesConstant;
import by.epam.learn.mudrahelau.constant.ParameterConstant;
import by.epam.learn.mudrahelau.model.User;
import by.epam.learn.mudrahelau.service.AdminService;
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
    private static final String COMMAND_NAME = "add_tariff";


    public AddTariffServletCommand(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(PagesConstant.MAIN_PAGE);
        User user = (User) request.getSession().getAttribute(ParameterConstant.USER);
        if (user != null) {

            if (AdminValidator.checkUserIsAdmin(user)) {
                String title = request.getParameter(ParameterConstant.TITLE);
                int speed = Integer.parseInt(request.getParameter(ParameterConstant.SPEED));
                BigDecimal price = new BigDecimal(request.getParameter(ParameterConstant.PRICE));
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
        return COMMAND_NAME;
    }
}
