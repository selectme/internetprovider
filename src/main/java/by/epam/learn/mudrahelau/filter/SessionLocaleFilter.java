package by.epam.learn.mudrahelau.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class SessionLocaleFilter implements Filter {
    public static final String SESSION_LOCALE = "sessionLocale";
    public static final String ATTRIBUTE_LANG = "lang";

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        if (req.getParameter(SESSION_LOCALE) != null) {
            req.getSession().setAttribute(ATTRIBUTE_LANG, req.getParameter(SESSION_LOCALE));
        }
        chain.doFilter(request, response);
    }

    public void destroy() {
    }

    public void init(FilterConfig arg0) {
    }
}
