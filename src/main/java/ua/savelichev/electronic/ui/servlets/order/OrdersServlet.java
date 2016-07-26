package ua.savelichev.electronic.ui.servlets.order;

import ua.savelichev.electronic.domain.entity.Order;
import ua.savelichev.electronic.domain.entity.User;
import ua.savelichev.electronic.domain.services.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/orders")
public class OrdersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        OrderService orderManager = new OrderService();

        HttpSession session = req.getSession();

        User user= (User) session.getAttribute("user");

        List<Order> orders= orderManager.getUserOrders(user);

        session.setAttribute("orders",orders);

        req.getRequestDispatcher("META-INF/view/orders.jsp").forward(req, resp);
    }
}
