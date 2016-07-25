package ua.savelichev.electronic.ui.servlets.product.notebook;

import ua.savelichev.electronic.domain.managers.product.NotebookManager;
import ua.savelichev.electronic.domain.entity.Notebook;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add-notebook")
public class AddNotebookServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("META-INF/view/product/addNotebook.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        NotebookManager notebookManager = new NotebookManager();
        Notebook notebook = new Notebook();

        notebook.setCategory(req.getParameter("category"));
        notebook.setProducer(req.getParameter("producer"));
        notebook.setModel(req.getParameter("model"));
        notebook.setPrice(Integer.valueOf(req.getParameter("price")));
        notebook.setDescription(req.getParameter("description"));
        notebook.setDisplayDiagonal(req.getParameter("displayDiagonal"));
        notebook.setProcessor(req.getParameter("processor"));
        notebook.setRam(Integer.valueOf(req.getParameter("ram")));
        notebook.setHdd(Integer.valueOf(req.getParameter("hdd")));
        notebook.setImageRef(req.getParameter("imageRef"));

        notebookManager.addNotebook(notebook);

        resp.sendRedirect("add-notebook");

    }
}
