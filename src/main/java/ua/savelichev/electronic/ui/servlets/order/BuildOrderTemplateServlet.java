package ua.savelichev.electronic.ui.servlets.order;

import ua.savelichev.electronic.dao.DAOFactory;
import ua.savelichev.electronic.domain.entity.Cart;
import ua.savelichev.electronic.domain.entity.interfaces.IOrder;
import ua.savelichev.electronic.domain.entity.User;
import ua.savelichev.electronic.domain.services.IOrderService;
import ua.savelichev.electronic.domain.services.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/build-order-template")
public class BuildOrderTemplateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        if (session.getAttribute("user") == null) {
            resp.sendRedirect("sign-in");
            return;
        }

        if (session.getAttribute("cart") == null) {
            resp.sendRedirect("cart");
            return;
        }

        User user = (User) session.getAttribute("user");
        Cart cart = (Cart) session.getAttribute("cart");
        IOrderService orderService = new OrderService(DAOFactory.getInstance());
        IOrder orderTemplate = orderService.buildOrderTemplate(user, cart);
        session.setAttribute("orderTemplate", orderTemplate);
        resp.sendRedirect("order-template");
    }
}
