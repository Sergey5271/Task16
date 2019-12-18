package servlet;

import bean.RegistrationFormBean;
import captcha.CaptchaGenerator;
import entity.Captcha;
import entity.Role;
import entity.User;
import image.Image;
import service.CaptchaService;
import service.UserService;
import strategy.CaptchaStrategy;
import util.ValidationRegistration;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;


@WebServlet("/signUp")
@MultipartConfig(maxFileSize = 20971520)
public class RegistrationServlet extends HttpServlet implements Serializable {

    public static final Logger LOGGER = Logger.getLogger(CaptchaServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)  {
        HttpSession httpSession = req.getSession();

        CaptchaGenerator captchaGenerator = new CaptchaGenerator();

        Captcha captcha = captchaGenerator.generationCaptcha();
        CaptchaStrategy captchaStrategy = (CaptchaStrategy) getServletContext().getAttribute("captchaStrategy");
        CaptchaService captchaService = (CaptchaService) getServletContext().getAttribute("captchaService");
        captchaService.save(captcha);
        captchaStrategy.saveCaptcha(req, resp, captcha);
        RegistrationFormBean registrationFormBean = (RegistrationFormBean) httpSession.getAttribute("bean");
        HashMap<String, String> errors = (HashMap) httpSession.getAttribute("errors");
        httpSession.removeAttribute("bean");
        httpSession.removeAttribute("errors");
        if (registrationFormBean != null && errors != null) {
            req.setAttribute("bean", registrationFormBean);
            req.setAttribute("errors", errors);
        } else {
            req.setAttribute("bean", new RegistrationFormBean());
            req.setAttribute("errors", new HashMap<>());
        }
        try {
            req.getRequestDispatcher("signUp.jsp").forward(req, resp);
        } catch (ServletException | IOException e) {
            LOGGER.log(Level.SEVERE, "Error ", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        Part part = tryGetPart(req);
        String firstName = req.getParameter("firstName");
        String secondName = req.getParameter("secondName");
        String email = req.getParameter("emailAddress");
        String userPassword = req.getParameter("password");
        String image = req.getParameter("image");
        String code = req.getParameter("code");

        CaptchaStrategy captchaStrategy = (CaptchaStrategy) getServletContext().getAttribute("captchaStrategy");
        CaptchaService captchaService = (CaptchaService) getServletContext().getAttribute("captchaService");
        long captchaId = captchaStrategy.getCaptchaId(req, resp);
        Captcha captcha = captchaService.get(captchaId);

        RegistrationFormBean registrationFormBean = new RegistrationFormBean();

        registrationFormBean.setFirstName(firstName);
        registrationFormBean.setSecondName(secondName);
        registrationFormBean.setEmailAddress(email);
        registrationFormBean.setPassword(userPassword);
        registrationFormBean.setImage(image);

        ValidationRegistration validationRegistration = new ValidationRegistration();

        HashMap<String, String> errors = (HashMap<String, String>) validationRegistration.validate(registrationFormBean);

        if (captcha != null && code != null) {
            if (captcha.getCaptchaText().equals(code) && errors.isEmpty()) {
                try {
                    UserService userService = (UserService) getServletContext().getAttribute("userService");
                    User user = new User();
                    user.setFirstName(registrationFormBean.getFirstName());
                    user.setSecondName(registrationFormBean.getSecondName());
                    user.setEmailAddress(registrationFormBean.getEmailAddress());
                    user.setPassword(registrationFormBean.getPassword());
                    user.setImage(registrationFormBean.getImage());
                    user.setRole(Role.USER);
                    saveAvatar(user,part);
                    userService.add(user);
                    resp.sendRedirect("main.jsp");
                } catch (IOException e) {
                    LOGGER.log(Level.SEVERE, "Error ", e);
                }
            } else {
                httpSession.setAttribute("bean", registrationFormBean);
                httpSession.setAttribute("errors", errors);
                try {
                    resp.sendRedirect("signUp");
                } catch (IOException e) {
                    LOGGER.log(Level.SEVERE, "Error ", e);
                }
            }
        }
    }
    private Part tryGetPart(HttpServletRequest req) {
        Part part = null;
        try {
            part = req.getPart("image");
        } catch (IOException | ServletException e) {
            System.out.println(e);
        }
        return part;
    }

    private void saveAvatar(User user, Part part) {
        if (part != null) {
            Image imageProvider = (Image) getServletContext().getAttribute("image");
            String fileName = imageProvider.add(part, user.getEmailAddress());
            user.setImage(fileName);
        }
    }
}
