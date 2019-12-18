package filter;

import model.Cart;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Logger;

public class CartFilter implements Filter, Serializable {

    private static final long serialVersionUID = -4691121295725387264L;

    private static final Logger LOGGER = Logger.getLogger(CartFilter.class.getName());

    @Override
    public void init(FilterConfig filterConfig) {
        LOGGER.info("CartFilter was initialized");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            session.setAttribute("cart", new Cart());
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
