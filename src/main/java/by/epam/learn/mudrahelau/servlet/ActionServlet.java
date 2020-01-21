package by.epam.learn.mudrahelau.servlet;

import by.epam.learn.mudrahelau.dao.AdminDao;
import by.epam.learn.mudrahelau.dao.UserDao;
import by.epam.learn.mudrahelau.model.Client;
import by.epam.learn.mudrahelau.model.TariffPlan;
import by.epam.learn.mudrahelau.model.User;
import by.epam.learn.mudrahelau.role.Role;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ActionServlet extends HttpServlet {

    private static AdminDao adminDao;
    private static UserDao userDao;

    @Override
    public void init() throws ServletException {
        adminDao = new AdminDao();
        userDao = new UserDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action.equals("do_logout")) {
            doLogout(req, resp);
        } else if (action.equals("show_users")) {
            showUsersList(req, resp);
        } else if (action.equals("show_tariffs")) {
            showTariffsList(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action.equals("do_login")) {
            doLogin(req, resp);
        }
    }

    private void doLogin(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = userDao.getUser(login, password);
        String destPage = "login.jsp";
        if (user != null) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            if (user.getRole() == Role.CLIENT) {
                destPage = "index.jsp";
            } else {
                destPage = "index.jsp";
            }
        } else {
            String message = "Invalid login/password";
            req.setAttribute("message", message);
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(destPage);
        try {
            requestDispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void doLogout(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.removeAttribute("user");

            RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
            try {
                dispatcher.forward(req, resp);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void showUsersList(HttpServletRequest req, HttpServletResponse resp) {
        List<Client> clients = adminDao.retrieveClients();
        req.setAttribute("users", clients);
        RequestDispatcher dispatcher = req.getRequestDispatcher("users_list.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void showTariffsList(HttpServletRequest req, HttpServletResponse resp) {
        RequestDispatcher dispatcher;
        HttpSession session = req.getSession();
        List<TariffPlan> tariffPlans = new ArrayList<>();
        try {
            tariffPlans = adminDao.retrieveTariffPlans();
            req.setAttribute("tariffPlans", tariffPlans);
            dispatcher = req.getRequestDispatcher("list.jsp");
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
