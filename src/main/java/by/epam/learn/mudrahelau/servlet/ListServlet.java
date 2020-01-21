package by.epam.learn.mudrahelau.servlet;

import by.epam.learn.mudrahelau.dao.AdminDao;
import by.epam.learn.mudrahelau.dao.ProviderDaoImpl;
import by.epam.learn.mudrahelau.model.TariffPlan;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        RequestDispatcher dispatcher;
        HttpSession session = req.getSession();
        if (session != null) {
            try {
                AdminDao adminDao = new AdminDao();

                List<TariffPlan> tariffPlans = adminDao.retrieveTariffPlans();

                req.setAttribute("tariffPlans", tariffPlans);
                dispatcher = req.getRequestDispatcher("/list.jsp");
                dispatcher.forward(req, resp);

            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        } else {
            dispatcher = req.getRequestDispatcher("/login.jsp");
            try {
                dispatcher.forward(req, resp);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }
    }

}
