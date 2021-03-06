package by.epam.learn.mudrahelau.command;

import by.epam.learn.mudrahelau.constant.PagesConstant;
import by.epam.learn.mudrahelau.constant.ParameterConstant;
import by.epam.learn.mudrahelau.constant.RedirectConstants;
import by.epam.learn.mudrahelau.model.User;
import by.epam.learn.mudrahelau.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
/**
 * Implementation of {@link ServletCommand}.
 * LoginServletCommand is intended that {@link User} can log in in his account.
 *
 * @see ServletCommand
 * @see CommandStorage
 */
public class LoginServletCommand implements ServletCommand {
    /**
     * @see UserService
     */
    private UserService userService;
    /**
     * Constant name of the command.
     */
    private static final String COMMAND_NAME = "do_login";
    /**
     * Localized error message.
     */
    private static final String ERROR_MESSAGE = "label.error";

    public LoginServletCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String login = request.getParameter(ParameterConstant.LOGIN);
        String password = request.getParameter(ParameterConstant.PASSWORD);
        User user = userService.getUser(login, password);
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute(ParameterConstant.USER, user);
            response.sendRedirect(RedirectConstants.MAIN_PAGE_REDIRECT);
        } else {
            request.setAttribute(ParameterConstant.ERROR_ATTRIBUTE, ERROR_MESSAGE);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(PagesConstant.LOGIN_PAGE);
            requestDispatcher.forward(request,response);
        }
    }


    @Override
    public String getName() {
        return COMMAND_NAME;
    }
}
