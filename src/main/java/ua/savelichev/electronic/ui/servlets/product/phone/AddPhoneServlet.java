package ua.savelichev.electronic.ui.servlets.product.phone;

import ua.savelichev.electronic.dao.DAOFactory;
import ua.savelichev.electronic.domain.entity.Notebook;
import ua.savelichev.electronic.domain.entity.Phone;
import ua.savelichev.electronic.domain.services.product.NotebookService;
import ua.savelichev.electronic.domain.services.product.PhoneService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add-phone")
public class AddPhoneServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("META-INF/view/product/add-phone.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PhoneService phoneService = new PhoneService(DAOFactory.getInstance());
        Phone phone = new Phone();

        phone.setCategory(req.getParameter("category"));
        phone.setProducer(req.getParameter("producer"));
        phone.setModel(req.getParameter("model"));
        phone.setPrice(Integer.valueOf(req.getParameter("price")));
        phone.setDescription(req.getParameter("description"));
        phone.setDisplayDiagonal(req.getParameter("displayDiagonal"));
        phone.setOperationSystem(req.getParameter("os"));
        phone.setMainCamera(req.getParameter("mainCamera"));
        phone.setBatteryCapacity(req.getParameter("batteryCapacity"));
        phone.setImageRef(req.getParameter("imageRef"));

        phoneService.addPhone(phone);

        resp.sendRedirect("add-phone");

    }
}
