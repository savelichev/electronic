package ua.savelichev.electronic.ui.servlets.order;

import ua.savelichev.electronic.domain.entity.Cart;
import ua.savelichev.electronic.domain.entity.Order;
import ua.savelichev.electronic.domain.entity.User;
import ua.savelichev.electronic.domain.managers.IOrderManager;
import ua.savelichev.electronic.domain.managers.OrderManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/buildOrderTemplate")
public class BuildOrderTemplateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session=req.getSession();

        if(session.getAttribute("user")==null){
            resp.sendRedirect("signIn");
            return;
        }

        if (session.getAttribute("cart")==null){
            resp.sendRedirect("cart");
            return;
        }

        User user = (User) session.getAttribute("user");

        Cart cart = (Cart) session.getAttribute("cart");

        IOrderManager orderManager = new OrderManager();

        Order orderTemplate = orderManager.buildOrderTemplate(user,cart);

        session.setAttribute("orderTemplate", orderTemplate);

        resp.sendRedirect("order-template");
    }


}
