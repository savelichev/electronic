package ua.savelichev.electronic.ui.servlets.user;

import ua.savelichev.electronic.domain.services.UserService;
import ua.savelichev.electronic.domain.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/sign-in")
public class SignInServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("META-INF/view/user/sign-in.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserService userService = new UserService();
        User inUser = new User();

        inUser.setEmail(req.getParameter("email"));
        inUser.setPassword(req.getParameter("password"));

        User userFromDB = userService.getUserByEmail(inUser.getEmail());

        if (userFromDB.getPassword() == null) {
            resp.sendRedirect("sign-in");
        }

        if (!(inUser.getPassword().equals(userFromDB.getPassword()))) {
            resp.sendRedirect("sign-in");

        }

        req.getSession().setAttribute("user", userFromDB);
        resp.sendRedirect("index");


    }
}
