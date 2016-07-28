package ua.savelichev.electronic.ui.servlets.product.phone;

import ua.savelichev.electronic.domain.entity.Phone;
import ua.savelichev.electronic.domain.services.product.PhoneService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/phone")
public class PhoneServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Phone> listPhones = new PhoneService().getAllPhones();

        req.setAttribute("phones", listPhones);
        req.getRequestDispatcher("META-INF/view/product/phone.jsp").forward(req, resp);


    }
}
