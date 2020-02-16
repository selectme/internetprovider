package by.epam.learn.mudrahelau.command;

import by.epam.learn.mudrahelau.model.Client;
import by.epam.learn.mudrahelau.model.TariffPlan;
import by.epam.learn.mudrahelau.model.User;
import by.epam.learn.mudrahelau.service.AdminService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static by.epam.learn.mudrahelau.validator.AdminValidator.checkUserIsAdmin;

/**
 * @author Viktar on 16.02.2020
 */
public class ShowEditUserPageServletCommand implements ServletCommand {

    private AdminService adminService;

    public ShowEditUserPageServletCommand(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String destinationPage = "index.jsp";
        User user = (User) request.getSession().getAttribute("user");
        if (checkUserIsAdmin(user)) {
            long id = Long.parseLong(request.getParameter("user_id"));
            Client client = adminService.getClientById(id);
            List<TariffPlan> tariffPlans = adminService.retrieveTariffPlans();
            request.setAttribute("client", client);
            request.setAttribute("tariffPlans", tariffPlans);
            destinationPage = "edit_user_by_admin_page.jsp";
//            forwardToPage(req, resp, "edit_user_by_admin_page.jsp");
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(destinationPage);
        requestDispatcher.forward(request, response);
    }

    @Override
    public String getName() {
        return "show_edit_user_page_by_admin";
    }
}
