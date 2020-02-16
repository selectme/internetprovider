package by.epam.learn.mudrahelau.command;

import by.epam.learn.mudrahelau.model.TariffPlan;
import by.epam.learn.mudrahelau.service.AdminService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Viktar on 16.02.2020
 */
public class ShowTariffsServletCommand implements ServletCommand {

    private AdminService adminService;

    public ShowTariffsServletCommand(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<TariffPlan> tariffPlans = adminService.retrieveTariffPlans();
        request.setAttribute("tariffPlans", tariffPlans);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("tariff_plans_list.jsp");
        requestDispatcher.forward(request, response);
//        forwardToPage(req, resp, "tariff_plans_list.jsp");
    }

    @Override
    public String getName() {
        return "show_tariffs";
    }
}
