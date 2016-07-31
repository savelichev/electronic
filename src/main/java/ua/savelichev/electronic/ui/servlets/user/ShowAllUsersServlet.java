package ua.savelichev.electronic.ui.servlets.user;

import ua.savelichev.electronic.domain.entity.User;
import ua.savelichev.electronic.domain.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/show-all-users")
public class ShowAllUsersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = new UserService();
        List<User> users = userService.getAllUsers();

        req.setAttribute("users", users);
        req.getRequestDispatcher("META-INF/view/user/all-users.jsp").forward(req, resp);
    }
}
