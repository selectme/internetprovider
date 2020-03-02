package by.epam.learn.mudrahelau.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Filter that checks and sets language locale from session.
 */
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
