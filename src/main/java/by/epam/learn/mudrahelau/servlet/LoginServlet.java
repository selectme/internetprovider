package by.epam.learn.mudrahelau.servlet;

import by.epam.learn.mudrahelau.dao.UserDao;
import by.epam.learn.mudrahelau.model.User;
import by.epam.learn.mudrahelau.role.Role;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action.equals("login")) {
            doLogin(req);
        } else if (action.equals("createUser")) {
//            doUserCreation(req);
        } else if (action.equals("getUsers")) {

        }

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        UserDao userDao = new UserDao();
        User user = userDao.getUser(login, password);
        String destPage = "login.jsp";
//        if (user != null) {
//            HttpSession session = req.getSession();
//            session.setAttribute("user", user);
//            if (user.getRole() == Role.CLIENT) {
//                destPage = "index.jsp";
//            } else{
//                destPage = "notfound.jsp";
//            }
//        } else {
//            String message = "Invalid login/password";
//            req.setAttribute("message", message);
//        }
//        RequestDispatcher requestDispatcher = req.getRequestDispatcher(destPage);
//        requestDispatcher.forward(req, resp);
    }

    private void doLogin(HttpServletRequest req) {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

//        new UserService.login(log(););
    }
}
