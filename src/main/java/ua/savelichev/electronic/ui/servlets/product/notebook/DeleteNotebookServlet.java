package ua.savelichev.electronic.ui.servlets.product.notebook;

import org.apache.log4j.Logger;
import ua.savelichev.electronic.domain.services.product.NotebookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete-notebook")
public class DeleteNotebookServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(DeleteNotebookServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NotebookService notebookService = new NotebookService();
        String article = req.getParameter("notebookArticle");
        log.info("Sending request for deletion product with article: " + article);
        notebookService.deleteNotebookByArticle(article);
        resp.sendRedirect("notebook");
    }
}
