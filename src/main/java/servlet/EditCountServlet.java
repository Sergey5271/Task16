package servlet;

import model.Cart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;


@WebServlet("/editCount")
public class EditCountServlet extends HttpServlet {

    public static final Logger LOGGER = Logger.getLogger(EditCountServlet.class.getName());


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        try (Writer writer = response.getWriter()) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                int countProduct = Integer.parseInt(request.getParameter("count"));
                double price = cart.editCount(id, countProduct);
                String json = String.format("{\"itemPrice\": %.2f, \"totalPrice\": %.2f}", price, cart.getTotalPrice());
                writer.write(json);
            } catch (IllegalArgumentException e) {
                LOGGER.log(Level.SEVERE, "Error ", e);
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error ", e);
        }
    }
}
