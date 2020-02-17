package by.epam.learn.mudrahelau.servlet;

import by.epam.learn.mudrahelau.command.CommandStorage;
import by.epam.learn.mudrahelau.command.ServletCommand;
import by.epam.learn.mudrahelau.command.CommandStorage;
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
import by.epam.learn.mudrahelau.validator.LoginValidator;
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


    @Override
    public void init() {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String action = req.getParameter("action");
            ServletCommand command = CommandStorage.getInstance().getCommandByName(action);
            command.execute(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String action = req.getParameter("action");
            ServletCommand command = CommandStorage.getInstance().getCommandByName(action);
            command.execute(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
