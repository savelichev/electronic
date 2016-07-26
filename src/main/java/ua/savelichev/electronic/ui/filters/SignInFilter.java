package ua.savelichev.electronic.ui.filters;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/sign-in")
public class SignInFilter implements Filter {

    private static final Logger log = Logger.getLogger(SignInFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


        if (!((HttpServletRequest) servletRequest).getMethod().equals("POST")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        ((HttpServletRequest) servletRequest).getSession().setAttribute("badData", false);

        if (servletRequest.getParameter("email").equals("") || servletRequest.getParameter("password").equals("")) {
            log.debug("Input email or password is empty");
            ((HttpServletRequest) servletRequest).getSession().setAttribute("badData", true);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
