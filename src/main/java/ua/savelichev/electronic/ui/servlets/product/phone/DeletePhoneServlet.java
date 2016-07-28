package ua.savelichev.electronic.ui.servlets.product.phone;

import ua.savelichev.electronic.domain.services.product.PhoneService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete-phone")
public class DeletePhoneServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        new PhoneService().deletePhoneByArticle(req.getParameter("phoneArticle"));
        resp.sendRedirect("phone");
    }
}
