package ua.savelichev.electronic.ui.servlets.product.notebook;

import ua.savelichev.electronic.domain.services.product.NotebookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete-notebook")
public class DeleteNotebookServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        new NotebookService().deleteNotebook(req.getParameter("notebookArticle"));
        resp.sendRedirect("notebooks");
    }
}
