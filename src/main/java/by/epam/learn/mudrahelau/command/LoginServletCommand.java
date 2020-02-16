package by.epam.learn.mudrahelau.command;

import by.epam.learn.mudrahelau.model.User;
import by.epam.learn.mudrahelau.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServletCommand implements ServletCommand {
    public LoginServletCommand(UserService userService) {
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
//        User user = userService.getUser(login, password);
//        if (user != null) {
        HttpSession session = request.getSession();
//            session.setAttribute("user", user);
//            forwardToPage(request, resp, "index.jsp");
//        } else {
        String error = "Invalid login/password"; // TODO: thing about localization
        request.setAttribute("error", error);
//            forwardToPage(request, resp, "login.jsp");
    }


    @Override
    public String getName() {
        return "do_login";
    }
}
