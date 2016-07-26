package ua.savelichev.electronic.ui.servlets.user;

import org.apache.log4j.Logger;
import ua.savelichev.electronic.domain.services.UserService;
import ua.savelichev.electronic.domain.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/sign-in")
public class SignInServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(SignInServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("META-INF/view/user/sign-in.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if ((req.getSession().getAttribute("badData")).equals("true")) {
            resp.sendRedirect("sign-in");
            return;
        }

        UserService userService = new UserService();
        User inUser = new User();

        inUser.setEmail(req.getParameter("email"));
        inUser.setPassword(req.getParameter("password"));


        log.debug("User " + inUser.getEmail() + " try to sign in");

        User userFromDB = userService.getUserByEmail(inUser.getEmail());

        if (userFromDB.getPassword() == null) {
            resp.sendRedirect("sign-in");
            return;
        }

        if (!(inUser.getPassword().equals(userFromDB.getPassword()))) {
            resp.sendRedirect("sign-in");
            return;

        }

        req.getSession().setAttribute("user", userFromDB);
        resp.sendRedirect("index");


    }
}
