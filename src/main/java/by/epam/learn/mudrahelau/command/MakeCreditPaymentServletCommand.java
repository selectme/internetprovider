package by.epam.learn.mudrahelau.command;

import by.epam.learn.mudrahelau.model.Payment;
import by.epam.learn.mudrahelau.model.User;
import by.epam.learn.mudrahelau.payment.PaymentType;
import by.epam.learn.mudrahelau.service.ClientService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Viktar on 16.02.2020
 */
public class MakeCreditPaymentServletCommand implements ServletCommand {

    private ClientService clientService;

    public MakeCreditPaymentServletCommand(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            BigDecimal amount = new BigDecimal(request.getParameter("amount"));
            LocalDateTime time = LocalDateTime.now();
            Payment payment = new Payment(user.getId(), amount, PaymentType.CREDIT, time);
            clientService.makePayment(payment);
            response.sendRedirect("do?action=show_client_account_page&user_id=" + user.getId());
        } else {
            requestDispatcher.forward(request, response);
        }
    }

    @Override
    public String getName() {
        return "make_payment";
    }
}
