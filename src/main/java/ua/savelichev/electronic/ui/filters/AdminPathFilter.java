package ua.savelichev.electronic.ui.filters;

import org.apache.log4j.Logger;
import ua.savelichev.electronic.domain.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Filter checks all requests for for access rights.
 *
 * Checks user role if "adminPaths" contains request url.
 * Failing users rights to access url, filter sends redirect to "index",
 * logs info about user ip address and uri.
 */
@WebFilter("/*")
public class AdminPathFilter implements Filter {

    private static final Logger log = Logger.getLogger(AdminPathFilter.class);

    private List<String> adminPaths = Arrays.asList(
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

        if (!(adminPaths.contains(url))) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        HttpSession session = ((HttpServletRequest) servletRequest).getSession();
        User user = (User) session.getAttribute("user");


        if (user != null && user.getRole().equals("ADMIN")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            String userIP;
            String IP = ((HttpServletRequest) servletRequest).getHeader("HTTP_X_FORWARDED_FOR");
            if (IP != null) {
                userIP = IP;
            } else {
                userIP = servletRequest.getRemoteAddr();
            }
            log.info("User: \" " + userIP + "\" logon attempt ADMIN path: " + url);
            ((HttpServletResponse) servletResponse).sendRedirect("index");
        }


    }

    @Override
    public void destroy() {

    }
}
