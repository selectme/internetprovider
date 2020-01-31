package by.epam.learn.mudrahelau.servlet;

import by.epam.learn.mudrahelau.model.Client;
import by.epam.learn.mudrahelau.model.Payment;
import by.epam.learn.mudrahelau.model.TariffPlan;
import by.epam.learn.mudrahelau.model.User;
import by.epam.learn.mudrahelau.payment.PaymentType;
import by.epam.learn.mudrahelau.role.Role;
import by.epam.learn.mudrahelau.service.AdminService;
import by.epam.learn.mudrahelau.service.ClientService;
import by.epam.learn.mudrahelau.service.UserService;
import by.epam.learn.mudrahelau.status.ClientStatus;

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

        try {
            String action = req.getParameter("action");

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
            } else if (action.equals("show_edit_user_page_by_admin")) {
                showEditUSerPageByAdmin(req, resp);
            } else if (action.equals("show_edit_client_by_client_page")) {
                showEditClientPageByClient(req, resp);
            } else if (action.equals("show_edit_tariffplan_page")) {
                showEditTariffPlanPage(req, resp);
            } else if (action.equals("show_login_page")) {
                showLoginPage(req, resp);
            } else if (action.equals("show_client_account_page")) {
                showClientAccountPage(req, resp);
            } else if (action.equals("show_payment_page")) {
                showPaymentPage(req, resp);
            } else if (action.equals("show_change_tariff_page")) {
                showChangeTariffPage(req, resp);
            }
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String action = req.getParameter("action");
            if (action.equals("do_login")) {
                doLogin(req, resp);
            } else if (action.equals("add_tariff")) {
                addTariffPlan(req, resp);
            } else if (action.equals("add_user")) {
                addUSer(req, resp);
            } else if (action.equals("edit_user_by_admin")) {
                editClientByAdmin(req, resp);
            } else if (action.equals("edit_client_by_client")) {
                editClientByClient(req, resp);
            } else if (action.equals("editTariffPlan")) {
                editTariffPlan(req, resp);
            } else if (action.equals("make_payment")) {
                makeCreditPayment(req, resp);
            } else if (action.equals("change_tariff_plan")) {
                changeTariffPlan(req, resp);
            } else if (action.equals("delete_user")) {
                deleteUserById(req, resp);
            } else if (action.equals("delete_tariff_plan")) {
                deleteTariffPlanById(req, resp);
            }
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteTariffPlanById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int userId = Integer.parseInt(req.getParameter("tariff_id"));
        adminService.deleteTariffPlanById(userId);
        resp.sendRedirect("/do?action=show_tariffs");
    }

    private void deleteUserById(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        User user = (User) req.getSession().getAttribute("user");
        if (checkUserIsAdmin(user)) {
            long userId = Long.parseLong(req.getParameter("user_id"));
            adminService.deleteUserById(userId);
            resp.sendRedirect("/do?action=show_users");
        }else {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("index.jsp");
            requestDispatcher.forward(req, resp);
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

    private void addUSer(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        User user = (User) req.getSession().getAttribute("user");
        if (checkUserIsAdmin(user)) {
            String login = req.getParameter("login");
            String password = req.getParameter("password");
            String name = req.getParameter("name");
            String surname = req.getParameter("surname");
            Role role = Role.valueOf(req.getParameter("role"));
            adminService.createUser(login, password, name, surname, role);
            resp.sendRedirect("/do?action=show_users");
        } else {
            RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
            dispatcher.forward(req, resp);
        }
    }

    private void showEditUSerPageByAdmin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (checkUserIsAdmin(user)) {
            long id = Long.parseLong(req.getParameter("user_id"));
            Client client = adminService.getClientById(id);
            List<TariffPlan> tariffPlans = adminService.retrieveTariffPlans();
            req.setAttribute("client", client);
            req.setAttribute("tariffPlans", tariffPlans);
            RequestDispatcher dispatcher = req.getRequestDispatcher("edit_user_by_admin_page.jsp");
            dispatcher.forward(req, resp);
        } else {
            RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
            dispatcher.forward(req, resp);
        }

    }

    private void showEditClientPageByClient(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("user_id"));
        User user = (User) req.getSession().getAttribute("user");
        if (id == user.getId()) {
            Client client = adminService.getClientById(id);
            req.setAttribute("client", client);
            RequestDispatcher dispatcher = req.getRequestDispatcher("edit_client_by_client.jsp");
            dispatcher.forward(req, resp);
        } else {
            RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
            dispatcher.forward(req, resp);
        }
    }

    private void editTariffPlan(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        User user = (User) req.getSession().getAttribute("user");
        if (checkUserIsAdmin(user)) {
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
        } else {
            RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
            dispatcher.forward(req, resp);
        }
    }

    private void editClientByAdmin(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        User user = (User) req.getSession().getAttribute("user");
        if (checkUserIsAdmin(user)) {
            Client client = new Client();
            String login = req.getParameter("login");
            long clientId = Long.parseLong(req.getParameter("user_id"));
            String name = req.getParameter("name");
            String surname = req.getParameter("surname");
            int tariffPlanId;
            try {
                tariffPlanId = Integer.parseInt(req.getParameter("tariff_id"));
            } catch (NumberFormatException e) {
                tariffPlanId = 0;
            }
            ClientStatus status = ClientStatus.valueOf(req.getParameter("status").toUpperCase());
            client.setId(clientId);
            client.setLogin(login);
            client.setName(name);
            client.setSurname(surname);
            client.setStatus(status);
            adminService.editClientByAdmin(client);
            if (tariffPlanId != 0) {
                clientService.makePayment(new Payment(clientId, new BigDecimal(0), PaymentType.DEBIT, LocalDateTime.now()));
                adminService.assignTariffPlanToClient(clientId, tariffPlanId);
            }
            resp.sendRedirect("/do?action=show_users");
        } else {
            RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
            dispatcher.forward(req, resp);
        }
    }


    private void editClientByClient(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
//        long id = Long.parseLong(req.getParameter("user_id"));
//        User user = (User) req.getSession().getAttribute("user");
        Client client = new Client();
        long clientId = Long.parseLong(req.getParameter("user_id"));
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        client.setId(clientId);
        client.setName(name);
        client.setSurname(surname);
        clientService.editClientByClient(client);
        showClientAccountPage(req, resp);
    }

    private void showUsersList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (user.getRole() == Role.ADMIN) {
            List<Client> clients = adminService.retrieveClients();
            req.setAttribute("clients", clients);
            RequestDispatcher dispatcher = req.getRequestDispatcher("users_list.jsp");
            dispatcher.forward(req, resp);
        } else {
            RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
            dispatcher.forward(req, resp);
        }
    }

    private void showTariffsList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<TariffPlan> tariffPlans = adminService.retrieveTariffPlans();
        req.setAttribute("tariffPlans", tariffPlans);
        RequestDispatcher dispatcher = req.getRequestDispatcher("tariff_plans_list.jsp");
        dispatcher.forward(req, resp);
    }

    private void showAddTariffPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (checkUserIsAdmin(user)) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("add_tariff_plan.jsp");
            dispatcher.forward(req, resp);
        } else {
            RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
            dispatcher.forward(req, resp);
        }
    }

    private void showEditTariffPlanPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (checkUserIsAdmin(user)) {
            int id = Integer.parseInt(req.getParameter("tariff_id"));
            TariffPlan tariffPlan = adminService.getTariffPlanById(id);
            req.setAttribute("tariffPlan", tariffPlan);
            RequestDispatcher dispatcher = req.getRequestDispatcher("edit_tariffplan_page.jsp");
            dispatcher.forward(req, resp);
        } else {
            RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
            dispatcher.forward(req, resp);
        }
    }

    private void showAddUserPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        String destinationPage = "index.jsp";
        if (checkUserIsAdmin(user)) {
            destinationPage = "add_user.jsp";
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher(destinationPage);
        dispatcher.forward(req, resp);
    }

    private boolean checkUserIsAdmin(User user) {
        return user.getRole() == Role.ADMIN;
    }

    private void addTariffPlan(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (checkUserIsAdmin(user)) {
            String title = req.getParameter("title");
            int speed = Integer.parseInt(req.getParameter("speed"));
            BigDecimal price = new BigDecimal(req.getParameter("price"));
            adminService.createTariffPlan(title, speed, price);
            showTariffsList(req, resp);
        } else {
            RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
            dispatcher.forward(req, resp);
        }
    }

    private void showClientAccountPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("user_id"));
        User user = (User) req.getSession().getAttribute("user");
        long sessionUserId = user.getId();
        if (sessionUserId == id) {
            Client client = adminService.getClientById(id);
            req.setAttribute("client", client);
            RequestDispatcher dispatcher = req.getRequestDispatcher("client_account_page.jsp");
            dispatcher.forward(req, resp);
        } else {
            RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
            dispatcher.forward(req, resp);
        }
    }

    private void showPaymentPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("user_id"));
        User user = (User) req.getSession().getAttribute("user");
        if (id == user.getId()) {
            Client client = adminService.getClientById(id);
            req.setAttribute("client", client);
            RequestDispatcher dispatcher = req.getRequestDispatcher("make_payment_page.jsp");
            dispatcher.forward(req, resp);
        } else {
            RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
            dispatcher.forward(req, resp);
        }
    }

    private void makeCreditPayment(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long clientId = Long.parseLong(req.getParameter("user_id"));
        BigDecimal amount = new BigDecimal(req.getParameter("amount"));
        LocalDateTime time = LocalDateTime.now();
        Payment payment = new Payment(clientId, amount, PaymentType.CREDIT, time);
        clientService.makePayment(payment);
        showClientAccountPage(req, resp);
    }

    public void showChangeTariffPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("user_id"));
        User user = (User) req.getSession().getAttribute("user");
        if (id == user.getId()) {
            Client client = adminService.getClientById(id);
            TariffPlan tariff = adminService.getTariffPlanByClientId(id);
            List<TariffPlan> tariffPlans = adminService.retrieveTariffPlans();
            req.setAttribute("client", client);
            req.setAttribute("tariff", tariff);
            req.setAttribute("tariffPlans", tariffPlans);
            RequestDispatcher dispatcher = req.getRequestDispatcher("change_tariff_page.jsp");
            dispatcher.forward(req, resp);
        } else {
            RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
            dispatcher.forward(req, resp);
        }
    }

    public void changeTariffPlan(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long clientId = Long.parseLong(req.getParameter("user_id"));
        int tariffId = Integer.parseInt(req.getParameter("tariff_id"));
        BigDecimal clientMoney = clientService.retrieveClientMoneyAmountByClientId(clientId);
        BigDecimal tariffPlanPrice = adminService.getTariffPlanById(tariffId).getPrice();
        if (clientMoney != null && clientMoney.compareTo(tariffPlanPrice) >= 0) {
            Payment payment = new Payment(clientId, tariffPlanPrice.negate(), PaymentType.DEBIT, LocalDateTime.now());
            clientService.makePayment(payment);
            adminService.assignTariffPlanToClient(clientId, tariffId);
            showClientAccountPage(req, resp);
        } else {
            req.setAttribute("message", "You don't have enough money");
            showChangeTariffPage(req, resp);
        }
    }


}
