package servlet;


import entity.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/signIn")
public class LoginServlet extends HttpServlet {

    public static final Logger LOGGER = Logger.getLogger(CaptchaServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            HttpSession session = request.getSession();
            String errorMessage = (String) session.getAttribute("errorMessage");
            session.removeAttribute("errorMessage");
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("signIn.jsp").forward(request, response);
        }catch (IOException | ServletException e){
            LOGGER.log(Level.SEVERE, "Error ", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();

        if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
            try {
                session.setAttribute("errorMessage", "Login/Password can't be empty");
                request.getRequestDispatcher("signIn.jsp").forward(request, response);
            }catch (IOException | ServletException e){
                LOGGER.log(Level.SEVERE, "Error ", e);
            }
        } else {
            UserService userService = (UserService) getServletContext().getAttribute("userService");
            User user = userService.getByEmail(email);
            if (user == null || !password.equals(user.getPassword())) {
                try {
                    session.setAttribute("errorMessage", "Cannot find user with such login/password");
                    response.sendRedirect("signIn.jsp");
                }catch (IOException e){
                    LOGGER.log(Level.SEVERE, "Error ", e);
                }
            } else {
                try {
                    response.sendRedirect("main.jsp");
                    session.setAttribute("user", user);
                }catch (IOException e){
                    LOGGER.log(Level.SEVERE, "Error ", e);
                }
            }
        }
    }
}
