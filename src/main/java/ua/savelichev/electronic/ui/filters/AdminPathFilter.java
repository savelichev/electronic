package ua.savelichev.electronic.ui.filters;

import ua.savelichev.electronic.domain.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebFilter("/*")
public class AdminPathFilter implements Filter {

    private List<String> pathFilter = Arrays.asList(
            "/show-all-users",
            "/block-user",
            "/show-user-by-email",
            "/unblock-user",
            "/add-notebook",
            "/delete-notebook",
            "/admin-page"
    );


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        String url = ((HttpServletRequest) servletRequest).getRequestURI();

        if (!(pathFilter.contains(url))) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        HttpSession session = ((HttpServletRequest) servletRequest).getSession();
        User user = (User) session.getAttribute("user");

        if (user != null && user.getRole().equals("ADMIN")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        } else {
            ((HttpServletResponse) servletResponse).sendRedirect("index");
        }


    }

    @Override
    public void destroy() {

    }
}
