package ua.savelichev.electronic.ui.user;

import ua.savelichev.electronic.domain.managers.UserManager;
import ua.savelichev.electronic.domain.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/signIn")
public class SignInServlet extends HttpServlet {

    User inUser;
    UserManager userManager;
    User userFromDB;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("view/user/signIn.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        userManager = new UserManager();
        inUser = new User();
        inUser.setEmail(req.getParameter("email"));
        inUser.setPassword(req.getParameter("password"));

        userFromDB = userManager.getUserByEmail(inUser.getEmail());

        if (userFromDB.getPassword() == null) {
            resp.sendRedirect("signIn");
        }

        if (!(inUser.getPassword().equals(userFromDB.getPassword()))) {
            resp.sendRedirect("signIn");

        } else {
            req.getSession().setAttribute("user", userFromDB);
            resp.sendRedirect("index");
        }


    }
}
