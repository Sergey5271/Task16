package locale;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class CookieLocaleStrategy implements LocaleStrategy {


    @Override
    public Locale getLocale(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equalsIgnoreCase("locale")) {
                    return new Locale(cookie.getValue());
                }
            }
        }
        return null;
    }

    @Override
    public void saveLocale(ServletRequest request, ServletResponse response, Locale locale) {
        HttpServletResponse res = (HttpServletResponse) response;
        ServletContext context = request.getServletContext();
        Cookie cookie = new Cookie("locale", locale.getLanguage());
        cookie.setMaxAge((Integer) context.getAttribute("localeCookieMaxAge"));
        res.addCookie(cookie);
    }
}
