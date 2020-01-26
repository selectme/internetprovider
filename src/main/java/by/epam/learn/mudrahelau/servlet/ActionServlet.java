package by.epam.learn.mudrahelau.servlet;

import by.epam.learn.mudrahelau.dao.AdminDao;
import by.epam.learn.mudrahelau.dao.ClientDao;
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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ActionServlet extends HttpServlet {

    private static AdminDao adminDao;
    private static UserDao userDao;

    @Override
    public void init() {
        adminDao = new AdminDao();
        userDao = new UserDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String action = req.getParameter("action");
        try {
            if (action.equals("do_logout")) {
                doLogout(req, resp);
            } else if (action.equals("show_users")) {
                showUsersList(req, resp);
            } else if (action.equals("show_tariffs")) {
                showTariffsList(req, resp);
            } else if (action.equals("add_tariff_page")) {
                showAddTariffPage(req, resp);
            } else if (action.equals("show_add_client_page")) {
                showAddUserPage(req, resp);
            } else if (action.equals("show_edit_user_page")) {
                showEditUSerPage(req, resp);
            } else if (action.equals("show_edit_tariffplan_page")) {
                showEditTariffPlanPage(req, resp);
            } else if (action.equals("show_login_page")) {
                showLoginPage(req, resp);
            } else if (action.equals("show_client_account_page")) {
                showClientAccountPage(req, resp);
            }
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action.equals("do_login")) {
            doLogin(req, resp);
        } else if (action.equals("add_tariff")) {
            addTariffPlan(req, resp);
        } else if (action.equals("add_user")) {
            addUSer(req, resp);
        } else if (action.equals("edit")) {
            editClient(req, resp);
        } else if (action.equals("editTariffPlan")) {
            editTariffPlan(req, resp);
        }
    }


    private void showLoginPage(HttpServletRequest req, HttpServletResponse resp) {
        RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
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
            session.invalidate();
            RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
            try {
                dispatcher.forward(req, resp);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void addUSer(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        Role role = Role.valueOf(req.getParameter("role"));
        User user = new User(login, password, name, surname, role);
        adminDao.createUser(login, password, name, surname, role);
        try {
            resp.sendRedirect("/do?action=show_users");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showEditUSerPage(HttpServletRequest req, HttpServletResponse resp) {
        long id = Long.parseLong(req.getParameter("user_id"));
        Client client = adminDao.findClientById(id);
        List<TariffPlan> tariffPlans = adminDao.retrieveTariffPlans();
        req.setAttribute("client", client);
        req.setAttribute("tariffPlans", tariffPlans);
        RequestDispatcher dispatcher = req.getRequestDispatcher("edit_user_page.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void editTariffPlan(HttpServletRequest req, HttpServletResponse resp) {
        TariffPlan tariffPlan = new TariffPlan();
        int id = Integer.parseInt(req.getParameter("id"));
        System.out.println(id);
        String title = req.getParameter("title");
        int speed = Integer.parseInt(req.getParameter("speed"));
        BigDecimal price = new BigDecimal(req.getParameter("price"));
        tariffPlan.setId(id);
        tariffPlan.setTitle(title);
        tariffPlan.setSpeed(speed);
        tariffPlan.setPrice(price);
        System.out.println(tariffPlan);
        adminDao.editTariffPlan(tariffPlan);
        try {
            resp.sendRedirect("/do?action=show_tariffs");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void editClient(HttpServletRequest req, HttpServletResponse resp) {
        Client client = new Client();
        long id = Long.parseLong(req.getParameter("id"));
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        int tariffPlanId = 0;
        try {
            tariffPlanId = Integer.parseInt(req.getParameter("tariff_id"));
        } catch (NumberFormatException e) {
            System.out.println("tariff id = 0");
        }
        client.setId(id);
        client.setName(name);
        client.setSurname(surname);
        ClientDao clientDao = new ClientDao();
        clientDao.editClient(client);
        adminDao.assignTariffPlanToClient(id, tariffPlanId);
        try {
            resp.sendRedirect("/do?action=show_users");
        } catch (IOException e) {
            e.printStackTrace();
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
        try {
            List<TariffPlan> tariffPlans = adminDao.retrieveTariffPlans();
            req.setAttribute("tariffPlans", tariffPlans);
            RequestDispatcher dispatcher = req.getRequestDispatcher("tariff_plans_list.jsp");
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void showAddTariffPage(HttpServletRequest req, HttpServletResponse resp) {
        RequestDispatcher dispatcher = req.getRequestDispatcher("add_tariff_plan.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }


    private void showEditTariffPlanPage(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("tariff_id"));
        TariffPlan tariffPlan = adminDao.findTariffPlanById(id);
        req.setAttribute("tariffPlan", tariffPlan);
        RequestDispatcher dispatcher = req.getRequestDispatcher("edit_tariffplan_page.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void showAddUserPage(HttpServletRequest req, HttpServletResponse resp) {
        RequestDispatcher dispatcher = req.getRequestDispatcher("add_user.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void addTariffPlan(HttpServletRequest req, HttpServletResponse resp) {
        String title = req.getParameter("title");
        int speed = Integer.parseInt(req.getParameter("speed"));
        BigDecimal price = new BigDecimal(req.getParameter("price"));
        adminDao.createTariffPlan(title, speed, price);
        try {
            resp.sendRedirect("/do?action=show_tariffs");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showClientAccountPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("client_account_page.jsp");
        dispatcher.forward(req, resp);
    }

}
