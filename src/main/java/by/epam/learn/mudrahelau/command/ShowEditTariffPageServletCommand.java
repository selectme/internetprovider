package by.epam.learn.mudrahelau.command;

import by.epam.learn.mudrahelau.model.TariffPlan;
import by.epam.learn.mudrahelau.model.User;
import by.epam.learn.mudrahelau.service.AdminService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.learn.mudrahelau.validator.AdminValidator.checkUserIsAdmin;

/**
 * @author Viktar on 16.02.2020
 */
public class ShowEditTariffPageServletCommand implements ServletCommand {

    private AdminService adminService;

    public ShowEditTariffPageServletCommand(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String destinationPage = "index.jsp";
        User user = (User) request.getSession().getAttribute("user");
        if (checkUserIsAdmin(user)) {
            int id = Integer.parseInt(request.getParameter("tariff_id"));
            TariffPlan tariffPlan = adminService.getTariffPlanById(id);
            request.setAttribute("tariffPlan", tariffPlan);
            destinationPage = "edit_tariffplan_page.jsp";
//            forwardToPage(req, resp, "edit_tariffplan_page.jsp");
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(destinationPage);
        requestDispatcher.forward(request, response);
    }

    @Override
    public String getName() {
        return "show_edit_tariffplan_page";
    }
}
