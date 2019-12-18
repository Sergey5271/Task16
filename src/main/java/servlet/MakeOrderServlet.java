package servlet;

import entity.Book;
import entity.Order;
import entity.OrderDetails;
import entity.User;
import model.Cart;
import service.ServiceOrder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/makeOrder")
public class MakeOrderServlet extends HttpServlet {
    public static final Logger LOGGER = Logger.getLogger(MakeOrderServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            request.getRequestDispatcher("makeOrder.jsp").forward(request, response);
        } catch (IOException | ServletException e) {
            LOGGER.log(Level.SEVERE, "Error ", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        ServiceOrder orderService = (ServiceOrder) getServletContext().getAttribute("orderService");
        Order orders = orderForm(request);
        orderService.add(orders);
        try {
            response.sendRedirect(request.getContextPath() + "/main");
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error ", e);
        }
    }

    private Order orderForm(HttpServletRequest req) {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        Cart cart = (Cart) session.getAttribute("cart");
        Order order = new Order();
        order.setStatus("ACCEPTED");
        order.setDate(new Date());
        order.setUserId(user.getId());
        order.setCreditCard(req.getParameter("creditCardNum"));
        order.setAddress(req.getParameter("address"));
        List<OrderDetails> orderDetailsList = new ArrayList<>();
        for (Map.Entry<Book, Integer> entry : cart.getItems().entrySet()) {
            OrderDetails orderDetails = new OrderDetails(entry.getKey().getId(), order.getId(),
                    entry.getValue(), entry.getValue() * entry.getKey().getPrice());
            orderDetailsList.add(orderDetails);
        }
        order.setOrderDetails(orderDetailsList);
        return order;
    }
}
