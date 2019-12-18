package security;

import entity.User;
import security.permission.PermissionEvaluator;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Logger;

public class SecurityFilter implements Filter {

    public static final Logger LOGGER = Logger.getLogger(SecurityFilter.class.getName());
    private PermissionEvaluator permissionEvaluator;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ServletContext servletContext = filterConfig.getServletContext();
        LOGGER.info("Initialize security filter");
        this.permissionEvaluator = (PermissionEvaluator) servletContext.getAttribute("permissionEvaluator");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String url = req.getServletPath();
        if (permissionEvaluator.isPathSecured(url)) {
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("user");
            if (user == null) {
                session.setAttribute("redirect", req.getRequestURI());
                resp.sendRedirect(req.getContextPath() + "/main");
            } else {
                if (permissionEvaluator.hasAccess(user.getRole(), url)) {
                    filterChain.doFilter(servletRequest, servletResponse);
                } else {
                    resp.sendError(HttpServletResponse.SC_FORBIDDEN, "error.NotFounded");
                }
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
    }
}
