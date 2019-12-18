package servlet;

import entity.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoginServletTest {


    @Mock
    private ServletConfig servletConfig;

    @Mock
    private ServletContext servletContext;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession httpSession;

    @Mock
    private UserService userService;


    @InjectMocks
    private LoginServlet servlet;

    @Before
    public void beforeTest() throws ServletException {
        servlet = new LoginServlet();
        when(servletContext.getAttribute("userService")).thenReturn(userService);
        when(servletConfig.getServletContext()).thenReturn(servletContext);
        when(request.getSession()).thenReturn(httpSession);
        servlet.init(servletConfig);
    }


    @Test
    public void shouldLoginUserIfCredentialsIsCorrect() throws IOException, ServletException {
        User user = new User();
        String userEmail = "serhii.makarenko@epam.com";
        String userPassword = "password1234";
        when(userService.getByEmail(userEmail)).thenReturn(user);
        user.setEmailAddress(userEmail);
        user.setPassword(userPassword);
        when(request.getParameter("email")).thenReturn(userEmail);
        when(request.getParameter("password")).thenReturn(userPassword);
        ArgumentCaptor<User> argumentCaptor = ArgumentCaptor.forClass(User.class);
        servlet.doPost(request, response);
        verify(httpSession).setAttribute(eq("user"), argumentCaptor.capture());
        User loggedUser = argumentCaptor.getValue();
        Assert.assertEquals(user, loggedUser);
    }

    @Test
    public void shouldNotLoginUserIfPasswordIsWrong() throws IOException, ServletException {
        User user = new User();
        String userEmail = "serhii.makarenko@epam.com";
        String userPassword = "password";
        when(userService.getByEmail(userEmail)).thenReturn(user);
        user.setEmailAddress(userEmail);
        user.setPassword(userPassword);
        when(request.getParameter("email")).thenReturn(userEmail);
        when(request.getParameter("password")).thenReturn("wrong_password");
        servlet.doPost(request, response);
        verify(response).sendRedirect("signIn.jsp");
        assertNull(httpSession.getAttribute("user"));
    }
}