package ua.savelichev.electronic.ui.servlets.product.phone;

import org.apache.log4j.Logger;
import ua.savelichev.electronic.dao.DAOFactory;
import ua.savelichev.electronic.domain.services.product.PhoneService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete-phone")
public class DeletePhoneServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(DeletePhoneServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PhoneService phoneService = new PhoneService(DAOFactory.getInstance());
        String article = req.getParameter("phoneArticle");
        log.info("Sending request for deletion product with article: " + article);
        phoneService.deletePhoneByArticle(article);
        resp.sendRedirect("phone");
    }
}
