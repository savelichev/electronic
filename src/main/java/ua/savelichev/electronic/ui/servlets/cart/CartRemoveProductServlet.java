package ua.savelichev.electronic.ui.servlets.cart;

import ua.savelichev.electronic.domain.entity.ICart;
import ua.savelichev.electronic.domain.services.CartService;
import ua.savelichev.electronic.domain.services.ICartService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/cart-remove-product")
public class CartRemoveProductServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ICartService cartService = new CartService();

        HttpSession session = req.getSession();

        ICart cart = (ICart) session.getAttribute("cart");

        String productArticle = req.getParameter("productArticle");

        cart = cartService.removeProduct(cart, productArticle);

        session.setAttribute("cart", cart);


        resp.sendRedirect("cart");
    }
}
