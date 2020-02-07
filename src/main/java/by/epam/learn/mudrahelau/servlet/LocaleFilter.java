package by.epam.learn.mudrahelau.servlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Viktar on 07.02.2020
 */
public class LocaleFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;

        if (req.getParameter("sessionLocale") != null) {
            req.getSession().setAttribute("lang", req.getParameter("sessionLocale"));
        }
        filterChain.doFilter(servletRequest, servletResponse);

    }
}
