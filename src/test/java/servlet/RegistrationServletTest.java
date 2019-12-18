package servlet;

import bean.RegistrationFormBean;
import entity.Captcha;
import entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import service.CaptchaService;
import service.UserService;
import strategy.CaptchaStrategy;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class RegistrationServletTest {

    @Mock
    private ServletConfig servletConfig;

    @Mock
    private ServletContext servletContext;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private CaptchaService captchaService;

    @Mock
    private CaptchaStrategy captchaStrategy;

    @Mock
    private UserService userService;

    @Mock
    private HttpSession httpSession;

    @Mock
    private RequestDispatcher requestDispatcher;

    private RegistrationServlet servlet;

    @Before
    public void beforeTest() throws ServletException {
        servlet = new RegistrationServlet();
        when(servletContext.getAttribute("captchaService")).thenReturn(captchaService);
        when(servletContext.getAttribute("captchaStrategy")).thenReturn(captchaStrategy);
        when(servletContext.getAttribute("userService")).thenReturn(userService);
        when(servletConfig.getServletContext()).thenReturn(servletContext);
        when(request.getSession()).thenReturn(httpSession);
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
        servlet.init(servletConfig);
    }

    @Test
    public void shouldPassBeanAndErrorsFromSessionToRequest() {
        RegistrationFormBean bean = new RegistrationFormBean();
        Map<String, String> errors = new HashMap<>();
        errors.put("firstName", "First name should not be empty");
        when(httpSession.getAttribute("bean")).thenReturn(bean);
        when(httpSession.getAttribute("errors")).thenReturn(errors);
        servlet.doGet(request, response);
        verify(request).setAttribute("bean", bean);
        verify(request).setAttribute("errors", errors);
    }

    @Test
    public void shouldSaveUserWhenDataIsValid() throws ServletException, IOException {
        long expectedCaptchaId = new Date().getTime();
        int imgWidth = 10;
        int imgHeight = 10;
        int imgType = 1;
        Captcha captcha = new Captcha(new BufferedImage(imgWidth, imgHeight, imgType), "123456");
        captcha.setId(expectedCaptchaId);
        when(userService.getByEmail(any())).thenReturn(null);
        when(captchaService.get(expectedCaptchaId)).thenReturn(captcha);
        when(captchaStrategy.getCaptchaId(request, response)).thenReturn(captcha.getId());
        addUserParametersToRequest(request);
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        servlet.doPost(request, response);
        verify(userService).add(userArgumentCaptor.capture());
    }

    private void addUserParametersToRequest(HttpServletRequest request) {
        when(request.getParameter("firstName")).thenReturn("Serhii");
        when(request.getParameter("secondName")).thenReturn("Makarenko");
        when(request.getParameter("emailAddress")).thenReturn("serhii.makarenko@epam.com");
        when(request.getParameter("password")).thenReturn("qweQWERs2424D");
        when(request.getParameter("image")).thenReturn("robot.pnj");
        when(request.getParameter("code")).thenReturn("123456");
    }
}