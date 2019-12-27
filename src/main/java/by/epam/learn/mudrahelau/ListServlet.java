package by.epam.learn.mudrahelau;

import by.epam.learn.mudrahelau.dao.ProviderDaoImpl;
import by.epam.learn.mudrahelau.model.TariffPlan;
import by.epam.learn.mudrahelau.tarifftypes.TariffType;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

        try {
            ProviderDaoImpl providerDao = new ProviderDaoImpl();

            List<TariffPlan> tariffPlans = providerDao.retrieveTariffPlans();
            for (TariffPlan tariffPlan : tariffPlans) {
                System.out.println(tariffPlan);
            }
            req.setAttribute("tariffPlans", tariffPlans);
//            TariffPlan tariffPlan = new TariffPlan("super", 100, TariffType.UNLIM, 24);
//            String s = tariffPlan.toString();
//            req.setAttribute("test", s);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/list.jsp");
            dispatcher.forward(req, resp);

        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
