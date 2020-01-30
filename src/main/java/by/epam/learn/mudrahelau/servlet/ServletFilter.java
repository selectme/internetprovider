package by.epam.learn.mudrahelau.servlet;

import by.epam.learn.mudrahelau.dao.AdminDao;
import by.epam.learn.mudrahelau.dao.UserDao;
import by.epam.learn.mudrahelau.model.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Viktar on 30.01.2020
 */
public class ServletFilter implements Filter {

//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        servletRequest.setCharacterEncoding("UTF-8");
//        servletResponse.setCharacterEncoding("UTF-8");
//        HttpServletRequest req = (HttpServletRequest) servletRequest;
//        HttpServletResponse resp = (HttpServletResponse) servletResponse;
//        if (req.getParameter("user_id") != null) {
//            System.out.println("user id " + req.getParameter("user_id"));
//            long id = Long.parseLong(req.getParameter("user_id"));
//            User user = (User) req.getAttribute("user");
//            long sessionUserid = user.getId();
//
//
//        }
////        if (req.getSession(false) == null) {
////            System.out.println("no session");
////            RequestDispatcher requestDispatcher = req.getRequestDispatcher("login.jsp");
////            requestDispatcher.forward(req, resp);
////        } else if (req.getSession().getAttribute("user") == null) {
////            System.out.println("not logged in");
////            RequestDispatcher requestDispatcher = req.getRequestDispatcher("login.jsp");
////            requestDispatcher.forward(req, resp);
////        } else {
////            UserDao userDao = new UserDao();
////            String login = req.getParameter("login");
////            String password = req.getParameter("password");
////            User user = userDao.getUser(login, password);
////            req.setAttribute("user", user);
////            System.out.println("session is");
////        }
//
////
////        String destPage;
////        if (req.getSession(false) == null) {
////            destPage = "login.jsp";
////        } else {
////            destPage = "index.jsp";
////        }
////        RequestDispatcher requestDispatcher = req.getRequestDispatcher(destPage);
////        requestDispatcher.forward(req, resp);
//        filterChain.doFilter(req, resp);
//    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
