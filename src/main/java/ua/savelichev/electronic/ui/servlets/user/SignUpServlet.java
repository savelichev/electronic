package ua.savelichev.electronic.ui.servlets.user;

import ua.savelichev.electronic.dao.DAOFactory;
import ua.savelichev.electronic.domain.entity.User;
import ua.savelichev.electronic.domain.services.UserService;

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

        req.getSession().setAttribute("badSignUpData", false);

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String login = req.getParameter("login");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String cellNumber = req.getParameter("cellNumber");
        String address = req.getParameter("address");

        if (email.equals("") || password.equals("") || login.equals("")) {
            req.getSession().setAttribute("badSignUpData", true);
            resp.sendRedirect("sign-up");
            return;
        }

        User inUser = new User(email);
        inUser.setPassword(password);
        inUser.setLogin(login);
        inUser.setFirstName(firstName);
        inUser.setLastName(lastName);
        inUser.setCellNumber(cellNumber);
        inUser.setAddress(address);

        boolean isUserCreated = new UserService(DAOFactory.getInstance()).createUserIfNotExist(inUser);

        if (isUserCreated) {
            resp.sendRedirect("sign-in");
        } else {
            req.getSession().setAttribute("badSignUpData", true);
            resp.sendRedirect("sign-up");
        }

    }
}
