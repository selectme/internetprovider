package by.epam.learn.mudrahelau.command;

import by.epam.learn.mudrahelau.constant.PagesConstant;
import by.epam.learn.mudrahelau.constant.ParameterConstant;
import by.epam.learn.mudrahelau.constant.RedirectConstants;
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
 * Implementation of {@link ServletCommand}.
 * MakeCreditPaymentServletCommand is intended that {@link User} can make {@link Payment} and replenish his balance.
 *
 * @see ServletCommand
 * @see CommandStorage
 */
public class MakeCreditPaymentServletCommand implements ServletCommand {
    /**
     * @see ClientService
     */
    private ClientService clientService;
    /**
     * Constant name of the command.
     */
    private static final String COMMAND_NAME = "make_payment";

    public MakeCreditPaymentServletCommand(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(PagesConstant.MAIN_PAGE);
        User user = (User) request.getSession().getAttribute(ParameterConstant.USER);
        if (user != null) {
            BigDecimal amount = new BigDecimal(request.getParameter(ParameterConstant.AMOUNT));
            LocalDateTime time = LocalDateTime.now();
            Payment payment = new Payment(user.getId(), amount, PaymentType.CREDIT, time);
            clientService.makePayment(payment);
            response.sendRedirect(RedirectConstants.CLIENT_ACCOUNT_REDIRECT + user.getId());
        } else {
            requestDispatcher.forward(request, response);
        }
    }

    @Override
    public String getName() {
        return COMMAND_NAME;
    }
}
