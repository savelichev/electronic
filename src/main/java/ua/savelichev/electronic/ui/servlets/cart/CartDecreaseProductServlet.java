package ua.savelichev.electronic.ui.servlets.cart;


import ua.savelichev.electronic.domain.entity.ICart;
import ua.savelichev.electronic.domain.managers.CartManager;
import ua.savelichev.electronic.domain.managers.ICartManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/cart-decrease-product")
public class CartDecreaseProductServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ICartManager cartManager = new CartManager();

        HttpSession session = req.getSession();

        ICart cart = (ICart) session.getAttribute("cart");

        String productArticle = req.getParameter("productArticle");

        cart = cartManager.decreaseProductAmount(cart, productArticle);

        session.setAttribute("cart", cart);


        resp.sendRedirect("cart");
    }
}
