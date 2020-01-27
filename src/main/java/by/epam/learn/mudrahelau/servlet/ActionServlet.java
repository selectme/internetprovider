package by.epam.learn.mudrahelau.servlet;

import by.epam.learn.mudrahelau.dao.AdminDao;
import by.epam.learn.mudrahelau.dao.ClientDao;
import by.epam.learn.mudrahelau.dao.UserDao;
import by.epam.learn.mudrahelau.model.Client;
import by.epam.learn.mudrahelau.model.Payment;
import by.epam.learn.mudrahelau.model.TariffPlan;
import by.epam.learn.mudrahelau.model.User;
import by.epam.learn.mudrahelau.role.Role;
import by.epam.learn.mudrahelau.service.AdminService;
import by.epam.learn.mudrahelau.service.ClientService;
import by.epam.learn.mudrahelau.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ActionServlet extends HttpServlet {

    private static AdminService adminService;
    private static UserService userService;
    private static ClientService clientService;

    @Override
    public void init() {
        adminService = new AdminService();
        clientService = new ClientService();
        userService = new UserService();
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
            } else if (action.equals("show_payment_page")) {
                showPaymentPage(req, resp);
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
            editUser(req, resp);
        } else if (action.equals("editTariffPlan")) {
            editTariffPlan(req, resp);
        } else if (action.equals("make_payment")) {
            makePayment(req, resp);
        }
    }


    private void showLoginPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
            dispatcher.forward(req, resp);
    }

    private void doLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = userService.getUser(login, password);
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
        requestDispatcher.forward(req, resp);
    }

    private void doLogout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
            RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
            dispatcher.forward(req, resp);
        }
    }

    private void addUSer(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        Role role = Role.valueOf(req.getParameter("role"));
        adminService.createUser(login, password, name, surname, role);
        resp.sendRedirect("/do?action=show_users");
    }

    private void showEditUSerPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("user_id"));
        Client client = adminService.getClientById(id);
        List<TariffPlan> tariffPlans = adminService.retrieveTariffPlans();
        req.setAttribute("client", client);
        req.setAttribute("tariffPlans", tariffPlans);
        RequestDispatcher dispatcher = req.getRequestDispatcher("edit_user_page.jsp");
        dispatcher.forward(req, resp);
    }

    private void editTariffPlan(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        TariffPlan tariffPlan = new TariffPlan();
        int id = Integer.parseInt(req.getParameter("id"));
        String title = req.getParameter("title");
        int speed = Integer.parseInt(req.getParameter("speed"));
        BigDecimal price = new BigDecimal(req.getParameter("price"));
        tariffPlan.setId(id);
        tariffPlan.setTitle(title);
        tariffPlan.setSpeed(speed);
        tariffPlan.setPrice(price);
        adminService.editTariffPlan(tariffPlan);
        resp.sendRedirect("/do?action=show_tariffs");
    }

    private void editUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Client client = new Client();
        String login = req.getParameter("login");
        long clientId = Long.parseLong(req.getParameter("id"));
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        int tariffPlanId;
        try {
            tariffPlanId = Integer.parseInt(req.getParameter("tariff_id"));
        } catch (NumberFormatException e) {
            tariffPlanId = 0;
        }
        client.setId(clientId);
        client.setLogin(login);
        client.setName(name);
        client.setSurname(surname);
        ClientDao clientDao = new ClientDao();
        clientDao.editClient(client);
        adminService.assignTariffPlanToClient(clientId, tariffPlanId);
        resp.sendRedirect("/do?action=show_users");
    }

    private void showUsersList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Client> clients = adminService.retrieveClients();
        req.setAttribute("clients", clients);
        RequestDispatcher dispatcher = req.getRequestDispatcher("users_list.jsp");
        dispatcher.forward(req, resp);
    }

    private void showTariffsList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<TariffPlan> tariffPlans = adminService.retrieveTariffPlans();
        req.setAttribute("tariffPlans", tariffPlans);
        RequestDispatcher dispatcher = req.getRequestDispatcher("tariff_plans_list.jsp");
        dispatcher.forward(req, resp);
    }

    private void showAddTariffPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("add_tariff_plan.jsp");
        dispatcher.forward(req, resp);
    }

    private void showEditTariffPlanPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("tariff_id"));
        TariffPlan tariffPlan = adminService.getTariffPlanById(id);
        req.setAttribute("tariffPlan", tariffPlan);
        RequestDispatcher dispatcher = req.getRequestDispatcher("edit_tariffplan_page.jsp");
        dispatcher.forward(req, resp);
    }

    private void showAddUserPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("add_user.jsp");
        dispatcher.forward(req, resp);
    }

    private void addTariffPlan(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        int speed = Integer.parseInt(req.getParameter("speed"));
        BigDecimal price = new BigDecimal(req.getParameter("price"));
        adminService.createTariffPlan(title, speed, price);
        showTariffsList(req, resp);
    }

    private void showClientAccountPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("user_id"));
        Client client = adminService.getClientById(id);
        req.setAttribute("client", client);
        RequestDispatcher dispatcher = req.getRequestDispatcher("client_account_page.jsp");
        dispatcher.forward(req, resp);
    }

    private void showPaymentPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("user_id"));
        Client client = adminService.getClientById(id);
        req.setAttribute("client", client);
        RequestDispatcher dispatcher = req.getRequestDispatcher("make_payment_page.jsp");
        dispatcher.forward(req, resp);
    }

    private void makePayment(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long clientId = Long.parseLong(req.getParameter("user_id"));
        BigDecimal amount = new BigDecimal(req.getParameter("amount"));
        LocalDateTime time = LocalDateTime.now();
        Payment payment = new Payment(clientId, amount, time);
        clientService.makePayment(payment);
        showClientAccountPage(req, resp);
    }
}
