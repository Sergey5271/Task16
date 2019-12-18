package locale;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;


public class SessionLocaleStrategy implements LocaleStrategy {

    @Override
    public Locale getLocale(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        return (Locale) session.getAttribute("locale");
    }

    @Override
    public void saveLocale(ServletRequest request, ServletResponse response, Locale locale) {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        session.setAttribute("locale", locale);
    }
}
