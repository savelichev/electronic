package ua.savelichev.electronic.ui.servlets.user;

import ua.savelichev.electronic.domain.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/show-all-users")
public class ShowAllUsersServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("users",new UserService().getAllUsers());
        req.getRequestDispatcher("META-INF/view/user/all-users.jsp").forward(req,resp);
    }
}
