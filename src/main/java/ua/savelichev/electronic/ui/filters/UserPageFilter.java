package ua.savelichev.electronic.ui.filters;

import ua.savelichev.electronic.domain.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Filter sends redirect to admin-page if user requests "/user-page" and has role "ADMIN"
 */
@WebFilter("/user-page")
public class UserPageFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpSession session = ((HttpServletRequest) servletRequest).getSession();
        User user = (User) session.getAttribute("user");

        if (user != null && !user.getRole().equals("ADMIN")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else if (user != null && user.getRole().equals("ADMIN")) {
            ((HttpServletResponse) servletResponse).sendRedirect("admin-page");
        }
    }

    @Override
    public void destroy() {

    }
}
