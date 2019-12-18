package servlet;

import entity.Captcha;
import service.CaptchaService;
import strategy.CaptchaStrategy;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/captcha/CaptchaServlet")
public class CaptchaServlet extends HttpServlet {

    public static final Logger LOGGER = Logger.getLogger(CaptchaServlet.class.getName());

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("image/jpg");

        CaptchaStrategy captchaStrategy = (CaptchaStrategy) getServletContext().getAttribute("captchaStrategy");
        CaptchaService captchaService = (CaptchaService) getServletContext().getAttribute("captchaService");
        long captchaId = captchaStrategy.getCaptchaId(request, response);
        Captcha captcha = captchaService.get(captchaId);

        try {
            OutputStream osImage = response.getOutputStream();
            ImageIO.write(captcha.getBufferedImage(), "jpeg", osImage);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error ", e);
        }

        HttpSession session = request.getSession();
        session.setAttribute("captchaId", captcha.getId());
    }
}