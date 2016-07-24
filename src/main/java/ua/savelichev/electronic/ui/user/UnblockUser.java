package ua.savelichev.electronic.ui.user;

import ua.savelichev.electronic.domain.managers.UserManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/unblockUser")
public class UnblockUser extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        new UserManager().unblockUser(req.getParameter("userEmail"));

        resp.sendRedirect("showAllUsers");
    }
}
