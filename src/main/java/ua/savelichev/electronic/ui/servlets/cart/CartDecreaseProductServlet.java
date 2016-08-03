package ua.savelichev.electronic.ui.servlets.cart;


import ua.savelichev.electronic.domain.entity.Product;
import ua.savelichev.electronic.domain.entity.interfaces.ICart;
import ua.savelichev.electronic.domain.services.CartService;
import ua.savelichev.electronic.domain.services.ICartService;
import ua.savelichev.electronic.domain.services.product.ProductUtils;

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
        ICartService cartService = new CartService();
        HttpSession session = req.getSession();
        ICart cart = (ICart) session.getAttribute("cart");
        int productArticle = Integer.valueOf(req.getParameter("productArticle"));
        Product product = ProductUtils.getProductByArticle(productArticle);
        cart = cartService.decreaseProductAmount(cart, product);
        session.setAttribute("cart", cart);

        resp.sendRedirect("cart");
    }
}
