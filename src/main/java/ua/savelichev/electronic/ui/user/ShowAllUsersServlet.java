package ua.savelichev.electronic.ui.user;

import ua.savelichev.electronic.domain.managers.UserManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/showAllUsers")
public class ShowAllUsersServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("users",new UserManager().getAllUsers());
        req.getRequestDispatcher("view/user/allUsers.jsp").forward(req,resp);
    }
}
