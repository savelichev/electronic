package ua.savelichev.electronic.ui.servlets.user;

import org.apache.log4j.Logger;
import ua.savelichev.electronic.dao.DAOFactory;
import ua.savelichev.electronic.domain.entity.User;
import ua.savelichev.electronic.domain.services.UserService;

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

        HttpSession session = req.getSession();
        session.setAttribute("badData",false);
        UserService userService = new UserService(DAOFactory.getInstance().getUserDAO());

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        log.debug("User " + email + " try to sign in");
        User userFromDB = userService.getUserByEmail(email);

        if (userFromDB == null || !(password.equals(userFromDB.getPassword()))) {
            session.setAttribute("badData",true);
            resp.sendRedirect("sign-in");
            return;
        }

        userFromDB.setPassword(null);
        session.setAttribute("user", userFromDB);

        resp.sendRedirect("user-page");
    }
}
