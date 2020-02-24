package by.epam.learn.mudrahelau.command;

import by.epam.learn.mudrahelau.constant.ParameterConstant;
import by.epam.learn.mudrahelau.constant.RedirectConstants;
import by.epam.learn.mudrahelau.model.Payment;
import by.epam.learn.mudrahelau.model.User;
import by.epam.learn.mudrahelau.payment.PaymentType;
import by.epam.learn.mudrahelau.service.AdminService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Viktar on 16.02.2020
 */
public class ChangeTariffPlanServletCommand implements ServletCommand {

    private AdminService adminService;
    private static final String COMMAND_NAME = "change_tariff_plan";

    public ChangeTariffPlanServletCommand(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        User user = (User) request.getSession().getAttribute(ParameterConstant.USER);
        if (user != null) {
            long clientId = Long.parseLong(request.getParameter(ParameterConstant.USER_ID));
            int tariffId = Integer.parseInt(request.getParameter(ParameterConstant.TARIFF_ID));
            BigDecimal tariffPlanPrice = adminService.getTariffPlanById(tariffId).getPrice();
                Payment payment = new Payment(clientId, tariffPlanPrice.negate(), PaymentType.DEBIT, LocalDateTime.now());
                adminService.makePaymentAndChangeTariff(clientId, tariffId, payment);
                response.sendRedirect(RedirectConstants.CLIENT_ACCOUNT_REDIRECT + user.getId());
        }
    }

    @Override
    public String getName() {
        return COMMAND_NAME;
    }
}
