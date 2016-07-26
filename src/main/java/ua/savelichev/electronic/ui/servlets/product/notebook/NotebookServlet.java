package ua.savelichev.electronic.ui.servlets.product.notebook;

import ua.savelichev.electronic.domain.services.product.NotebookService;
import ua.savelichev.electronic.domain.entity.Notebook;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/notebook")
public class NotebookServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Notebook> listNotebooks = new NotebookService().getAllNotebooks();

        req.setAttribute("notebooks",listNotebooks );
        req.getRequestDispatcher("META-INF/view/product/notebook.jsp").forward(req,resp);


    }
}
