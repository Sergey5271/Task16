package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/shoppingCart")
public class RedirectServletCart extends HttpServlet {

    public static final Logger LOGGER = Logger.getLogger(RedirectServletCart.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.getRequestDispatcher("shoppingCart.jsp").forward(request, response);
        } catch (IOException | ServletException e) {
            LOGGER.log(Level.SEVERE, "Error ", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }
}
