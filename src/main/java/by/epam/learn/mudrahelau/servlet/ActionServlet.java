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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    private static final Logger logger = LogManager.getLogger(ActionServlet.class);
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
            } else if (action.equals("show_clients_payments_page")) {
                showClientPaymentsPage(req, resp);
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

    private void deleteTariffPlanById(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        User user = (User) req.getSession().getAttribute("user");
        if (user != null) {
            if (checkUserIsAdmin(user)) {
                int userId = Integer.parseInt(req.getParameter("tariff_id"));
                adminService.deleteTariffPlanById(userId);
                resp.sendRedirect("/do?action=show_tariffs");
            }
        } else {
            forwardToPage(req, resp, "index.jsp");
        }
    }

    private void deleteUserById(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        User user = (User) req.getSession().getAttribute("user");
        if (user != null) {
            if (checkUserIsAdmin(user)) {
                long userId = Long.parseLong(req.getParameter("user_id"));
                adminService.deleteUserById(userId);
                resp.sendRedirect("/do?action=show_users");
            } else {
                forwardToPage(req, resp, "index.jsp");
            }
        } else {
            forwardToPage(req, resp, "index.jsp");
        }
    }

    private void showLoginPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forwardToPage(req, resp, "login.jsp");
    }

    private void doLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = userService.getUser(login, password);
        String destPage = "login.jsp";
        if (user != null) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            forwardToPage(req, resp, "index.jsp");
        } else {
            String message = "Invalid login/password";
            req.setAttribute("message", message);
        }
        forwardToPage(req, resp, destPage);
    }

    private void doLogout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        forwardToPage(req, resp, "index.jsp");
    }


    private void addUSer(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        User user = (User) req.getSession().getAttribute("user");
        if (user != null) {
            if (checkUserIsAdmin(user)) {
                String login = req.getParameter("login");
                String password = req.getParameter("password");
                String name = req.getParameter("name");
                String surname = req.getParameter("surname");
                Role role = Role.valueOf(req.getParameter("role"));
                adminService.createUser(login, password, name, surname, role);
                resp.sendRedirect("/do?action=show_users");
            } else {
                forwardToPage(req, resp, "index.jsp");
            }
        } else {
            resp.sendRedirect("/do?action=show_login_page");
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
            forwardToPage(req, resp, "edit_user_by_admin_page.jsp");
        } else {
            forwardToPage(req, resp, "index.jsp");
        }

    }

    private void showEditClientPageByClient(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (user != null) {
            long id = Long.parseLong(req.getParameter("user_id"));
            if (checkUserId(id, user)) {
                Client client = adminService.getClientById(id);
                req.setAttribute("client", client);
                forwardToPage(req, resp, "edit_client_by_client.jsp");
            } else {
                forwardToPage(req, resp, "index.jsp");
            }
        } else {
            forwardToPage(req, resp, "index.jsp");
        }
    }

    private boolean checkUserId(long id, User user) {
        if (user != null) {
            return id == user.getId();
        } else {
            return false;
        }
    }

    private void editTariffPlan(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        User user = (User) req.getSession().getAttribute("user");
        if (user != null) {
            if (checkUserIsAdmin(user)) {
                TariffPlan tariffPlan = new TariffPlan();
                tariffPlan.setId(Integer.parseInt(req.getParameter("id")));
                tariffPlan.setTitle(req.getParameter("title"));
                tariffPlan.setSpeed(Integer.parseInt(req.getParameter("speed")));
                tariffPlan.setPrice(new BigDecimal(req.getParameter("price")));
                adminService.editTariffPlan(tariffPlan);
                resp.sendRedirect("/do?action=show_tariffs");
            } else {
                forwardToPage(req, resp, "index.jsp");
            }
        } else {
            forwardToPage(req, resp, "index.jsp");
        }
    }

    private void editClientByAdmin(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        User user = (User) req.getSession().getAttribute("user");
        if (user != null) {
            if (checkUserIsAdmin(user)) {
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
                Client client = new Client();
                client.setId(clientId);
                client.setLogin(login);
                client.setName(name);
                client.setSurname(surname);
                client.setStatus(status);
                adminService.editClientByAdmin(client);
                if (tariffPlanId != adminService.getTariffPlanByClientId(clientId).getId()) {
                    if (tariffPlanId != 0) {
                        Payment payment = new Payment(clientId, new BigDecimal(0), PaymentType.DEBIT, LocalDateTime.now());
                        adminService.makePaymentAndChangeTariff(clientId, tariffPlanId, payment);
                    }
                }
                resp.sendRedirect("/do?action=show_users");
            } else {
                forwardToPage(req, resp, "index.jsp");
            }
        } else {
            forwardToPage(req, resp, "index.jsp");
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


    private void showClientPaymentsPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");

        if (user != null) {
            List<Payment> payments = clientService.retrievePayments(user.getId());
            req.setAttribute("payments", payments);
            forwardToPage(req, resp, "payments_page.jsp");
        } else {
            forwardToPage(req, resp, "index.jsp");
        }
    }

    private void showUsersList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (user != null) {
            if (user.getRole() == Role.ADMIN) {
                List<Client> clients = adminService.retrieveClients();
                req.setAttribute("clients", clients);
                forwardToPage(req, resp, "users_list.jsp");
            } else {
                forwardToPage(req, resp, "index.jsp");
            }
        } else {
//            forwardToPage(req, resp, "index.jsp");
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void showTariffsList(HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException {
        List<TariffPlan> tariffPlans = adminService.retrieveTariffPlans();
        req.setAttribute("tariffPlans", tariffPlans);
        forwardToPage(req, resp, "tariff_plans_list.jsp");
    }

    private void showAddTariffPage(HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (checkUserIsAdmin(user)) {
            forwardToPage(req, resp, "add_tariff_plan.jsp");
        } else {
            forwardToPage(req, resp, "index.jsp");
        }
    }

    private void showEditTariffPlanPage(HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (checkUserIsAdmin(user)) {
            int id = Integer.parseInt(req.getParameter("tariff_id"));
            TariffPlan tariffPlan = adminService.getTariffPlanById(id);
            req.setAttribute("tariffPlan", tariffPlan);
            forwardToPage(req, resp, "edit_tariffplan_page.jsp");
        } else {
            forwardToPage(req, resp, "index.jsp");
        }
    }

    private void showAddUserPage(HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        String destinationPage = "index.jsp";
        if (checkUserIsAdmin(user)) {
            destinationPage = "add_user.jsp";
        }
        forwardToPage(req, resp, destinationPage);
    }

    private boolean checkUserIsAdmin(User user) {
        if (user != null) {
            return user.getRole() == Role.ADMIN;
        } else {
            return false;
        }
    }

    private void addTariffPlan(HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (user != null) {
            if (checkUserIsAdmin(user)) {
                String title = req.getParameter("title");
                int speed = Integer.parseInt(req.getParameter("speed"));
                BigDecimal price = new BigDecimal(req.getParameter("price"));
                adminService.createTariffPlan(title, speed, price);
                showTariffsList(req, resp);
            } else {
                forwardToPage(req, resp, "index.jsp");
            }
        } else {
            forwardToPage(req, resp, "index.jsp");
        }
    }

    private void showClientAccountPage(HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (user != null) {
            long id = Long.parseLong(req.getParameter("user_id"));
            if (checkUserId(id, user)) {
                Client client = adminService.getClientById(id);
                req.setAttribute("client", client);
                forwardToPage(req, resp, "client_account_page.jsp");
            } else {
                forwardToPage(req, resp, "index.jsp");
            }
        } else {
            forwardToPage(req, resp, "index.jsp");
        }
    }

    private void forwardToPage(HttpServletRequest req, HttpServletResponse resp, String s) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher(s);
        dispatcher.forward(req, resp);
    }

    private void showPaymentPage(HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (user != null) {
            long id = Long.parseLong(req.getParameter("user_id"));
            if (checkUserId(id, user)) {
                Client client = adminService.getClientById(id);
                req.setAttribute("client", client);
                forwardToPage(req, resp, "make_payment_page.jsp");
            } else {
                forwardToPage(req, resp, "index.jsp");
            }
        } else {
            forwardToPage(req, resp, "index.jsp");
        }
    }

    private void makeCreditPayment(HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (user != null) {
            BigDecimal amount = new BigDecimal(req.getParameter("amount"));
            LocalDateTime time = LocalDateTime.now();
            Payment payment = new Payment(user.getId(), amount, PaymentType.CREDIT, time);
            clientService.makePayment(payment);
            showClientAccountPage(req, resp);
        } else {
            forwardToPage(req, resp, "index.jsp");
        }
    }

    public void showChangeTariffPage(HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException {

        User user = (User) req.getSession().getAttribute("user");
        if (user != null) {
            long id = Long.parseLong(req.getParameter("user_id"));
            if (checkUserId(id, user)) {
                Client client = adminService.getClientById(id);
                TariffPlan tariff = adminService.getTariffPlanByClientId(id);
                List<TariffPlan> tariffPlans = adminService.retrieveTariffPlans();
                req.setAttribute("client", client);
                req.setAttribute("tariff", tariff);
                req.setAttribute("tariffPlans", tariffPlans);
                forwardToPage(req, resp, "change_tariff_page.jsp");
            } else {
                forwardToPage(req, resp, "index.jsp");
            }
        } else {
            forwardToPage(req, resp, "index.jsp");
        }
    }

    public void changeTariffPlan(HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (user != null) {
            long clientId = Long.parseLong(req.getParameter("user_id"));
            int tariffId = Integer.parseInt(req.getParameter("tariff_id"));
            BigDecimal clientMoney = clientService.retrieveClientMoneyAmountByClientId(clientId);
            BigDecimal tariffPlanPrice = adminService.getTariffPlanById(tariffId).getPrice();
            if (clientMoney != null && clientMoney.compareTo(tariffPlanPrice) >= 0) {
                Payment payment = new Payment(clientId, tariffPlanPrice.negate(), PaymentType.DEBIT, LocalDateTime.now());
                adminService.makePaymentAndChangeTariff(clientId, tariffId, payment);
                showClientAccountPage(req, resp);
            } else {
                req.setAttribute("message", "You don't have enough money");
                showChangeTariffPage(req, resp);
            }
        } else {
            req.setAttribute("message", "You don't have enough money");
            showChangeTariffPage(req, resp);
        }
    }

}
