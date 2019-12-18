package servlet;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class LogoutServletTest {

    @Mock
    private ServletConfig servletConfig;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession httpSession;

    private LogoutServlet logoutServlet;

    @Before
    public void beforeTest() throws ServletException {
        logoutServlet = new LogoutServlet();
        when(request.getSession()).thenReturn(httpSession);
        logoutServlet.init(servletConfig);
    }


    @Test
    public void shouldLogout() throws IOException {
        String startPage = "main.jsp";
        logoutServlet.doPost(request, response);
        verify(httpSession).invalidate();
        verify(response).sendRedirect(startPage);

    }
}