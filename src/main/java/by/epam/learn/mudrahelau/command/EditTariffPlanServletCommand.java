package by.epam.learn.mudrahelau.command;

import by.epam.learn.mudrahelau.constant.PagesConstant;
import by.epam.learn.mudrahelau.constant.ParameterConstant;
import by.epam.learn.mudrahelau.model.TariffPlan;
import by.epam.learn.mudrahelau.model.User;
import by.epam.learn.mudrahelau.service.AdminService;
import by.epam.learn.mudrahelau.validator.TariffValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

import static by.epam.learn.mudrahelau.validator.AdminValidator.checkUserIsAdmin;

/**
 * @author Viktar on 16.02.2020
 */
public class EditTariffPlanServletCommand implements ServletCommand {
    private AdminService adminService;
    private static final String COMMAND_NAME = "edit_tariff_plan";
    private static final String ERROR_MESSAGE = "Values can't be negative";

    public EditTariffPlanServletCommand(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String destinationPage = PagesConstant.MAIN_PAGE;
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(destinationPage);
        User user = (User) request.getSession().getAttribute(ParameterConstant.USER);
        if (user != null) {
            if (checkUserIsAdmin(user)) {
                TariffPlan tariffPlan = new TariffPlan();
                tariffPlan.setId(Integer.parseInt(request.getParameter(ParameterConstant.ID)));
                tariffPlan.setTitle(request.getParameter(ParameterConstant.TITLE));
                tariffPlan.setSpeed(Integer.parseInt(request.getParameter(ParameterConstant.SPEED)));
                tariffPlan.setPrice(new BigDecimal(request.getParameter(ParameterConstant.PRICE)));
                if (TariffValidator.validateTariff(tariffPlan)) {
                    adminService.editTariffPlan(tariffPlan);
                    response.sendRedirect("/do?action=show_tariffs");
                } else {
                    response.sendRedirect("do?action=show_edit_tariffplan_page&tariff_id=" + tariffPlan.getId());
                }
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
