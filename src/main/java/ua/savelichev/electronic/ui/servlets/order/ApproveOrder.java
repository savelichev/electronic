package ua.savelichev.electronic.ui.servlets.order;

import ua.savelichev.electronic.domain.entity.Order;
import ua.savelichev.electronic.domain.services.IOrderService;
import ua.savelichev.electronic.domain.services.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/approve-order")
public class ApproveOrder extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        IOrderService orderService = new OrderService();

        HttpSession session = req.getSession();

        Order order = (Order) session.getAttribute("orderTemplate");

        String comment = req.getParameter("comment");

        String address = req.getParameter("address");

        String buyerName = req.getParameter("buyerName");

        String buyerCellNumber = req.getParameter("buyerCellNumber");

        orderService.approveOrder(order, comment, address, buyerName, buyerCellNumber);

        session.removeAttribute("cart");

        resp.sendRedirect("orders");


    }
}
