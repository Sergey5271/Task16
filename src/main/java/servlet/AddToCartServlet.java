package servlet;

import entity.Book;
import model.Cart;
import service.BookService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/addToCart")
public class AddToCartServlet extends HttpServlet {

    public static final Logger LOGGER = Logger.getLogger(AddToCartServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        String id = request.getParameter("id");
        BookService bookService = (BookService) getServletContext().getAttribute("bookService");
        try {
            Book book = bookService.get(Integer.parseInt(id));
            Cart cart = (Cart) request.getSession().getAttribute("cart");
            cart.add(book);
            try (Writer writer = response.getWriter()) {
                writer.write(cart.getItems().size());
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "Error ", e);
            }
        } catch (IllegalArgumentException e) {
            LOGGER.log(Level.SEVERE, "Error ", e);
        }
    }
}
