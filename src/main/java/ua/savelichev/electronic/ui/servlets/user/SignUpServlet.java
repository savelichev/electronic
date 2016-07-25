package ua.savelichev.electronic.ui.servlets.user;

import ua.savelichev.electronic.domain.managers.UserManager;
import ua.savelichev.electronic.domain.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/sign-up")
public class SignUpServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("META-INF/view/user/sign-up.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User inUser = new User();

        inUser.setEmail(req.getParameter("email"));
        inUser.setPassword(req.getParameter("password"));
        inUser.setLogin(req.getParameter("login"));
        inUser.setFirstName(req.getParameter("firstName"));
        inUser.setLastName(req.getParameter("lastName"));
        inUser.setCellNumber(req.getParameter("cellNumber"));
        inUser.setAddress(req.getParameter("address"));

        boolean isUserCreated = new UserManager().createUser(inUser);

        if (isUserCreated) {
            resp.sendRedirect("sign-in");
        } else {
            resp.sendRedirect("sign-up");
        }

    }
}