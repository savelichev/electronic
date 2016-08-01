package ua.savelichev.electronic.ui.servlets.user;

import ua.savelichev.electronic.dao.DAOFactory;
import ua.savelichev.electronic.domain.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/unblock-user")
public class UnblockUser extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        new UserService(DAOFactory.getInstance()).unblockUser(req.getParameter("userEmail"));

        resp.sendRedirect("show-all-users");
    }
}
