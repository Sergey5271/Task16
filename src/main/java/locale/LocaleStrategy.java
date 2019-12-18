package locale;


import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.Locale;

public interface LocaleStrategy {

    Locale getLocale(ServletRequest request, ServletResponse response);

    void saveLocale(ServletRequest request, ServletResponse response, Locale locale);
}
