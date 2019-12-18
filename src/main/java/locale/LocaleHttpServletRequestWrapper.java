package locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class LocaleHttpServletRequestWrapper  extends HttpServletRequestWrapper {

    private Locale locale;
    public LocaleHttpServletRequestWrapper(HttpServletRequest request, Locale locale) {
        super(request);
        this.locale = locale;
    }

    @Override
    public Locale getLocale() {
        return locale;
    }

    @Override
    public Enumeration<Locale> getLocales() {
        Set<Locale> locales = new HashSet<>();
        locales.add(locale);
        return Collections.enumeration(locales);
    }

}
