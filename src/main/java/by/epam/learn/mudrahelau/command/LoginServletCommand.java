package by.epam.learn.mudrahelau.command;

import by.epam.learn.mudrahelau.constant.PagesConstant;
import by.epam.learn.mudrahelau.constant.ParameterConstant;
import by.epam.learn.mudrahelau.model.User;
import by.epam.learn.mudrahelau.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServletCommand implements ServletCommand {
    private static final String COMMAND_NAME = "do_login";
    private UserService userService;

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
            response.sendRedirect("/");
        } else {
            String error = "Invalid login/password"; // TODO: thing about localization
            request.setAttribute("error", error);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(PagesConstant.LOGIN_PAGE);
            requestDispatcher.forward(request,response);
        }
    }


    @Override
    public String getName() {
        return COMMAND_NAME;
    }
}
