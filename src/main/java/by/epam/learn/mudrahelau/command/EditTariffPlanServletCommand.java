package by.epam.learn.mudrahelau.command;

import by.epam.learn.mudrahelau.model.TariffPlan;
import by.epam.learn.mudrahelau.model.User;
import by.epam.learn.mudrahelau.service.AdminService;

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

    public EditTariffPlanServletCommand(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            if (checkUserIsAdmin(user)) {
                TariffPlan tariffPlan = new TariffPlan();
                tariffPlan.setId(Integer.parseInt(request.getParameter("id")));
                tariffPlan.setTitle(request.getParameter("title"));
                tariffPlan.setSpeed(Integer.parseInt(request.getParameter("speed")));
                tariffPlan.setPrice(new BigDecimal(request.getParameter("price")));
                adminService.editTariffPlan(tariffPlan);
                response.sendRedirect("/do?action=show_tariffs");
            } else {
               requestDispatcher.forward(request,response);
            }
        } else {
            requestDispatcher.forward(request,response);
        }
    }

    @Override
    public String getName() {
        return "edit_tariff_plan";
    }
}
