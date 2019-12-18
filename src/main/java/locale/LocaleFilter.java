package locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

public class LocaleFilter implements Filter {

    public static final Logger LOGGER = Logger.getLogger(LocaleFilter.class.getName());

    private List<String> supportedLocales;
    private String defaultLocale;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ServletRequest requestWrapper;
        Locale locale;
        ServletContext context = servletRequest.getServletContext();
        LocaleStrategy localeStrategy = (LocaleStrategy) context.getAttribute("localeStrategy");
        if (checkLangIsPresent(servletRequest)) {
            locale = getLocaleFromRequestParam(servletRequest);
            localeStrategy.saveLocale(servletRequest, servletResponse, locale);
        } else {
            locale = localeStrategy.getLocale(servletRequest, servletResponse);
            if (locale == null) {
                locale = getLocaleFromRequest(servletRequest);
            }
        }
        requestWrapper = new LocaleHttpServletRequestWrapper((HttpServletRequest) servletRequest, locale);
        filterChain.doFilter(requestWrapper, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ServletContext context = filterConfig.getServletContext();
        this.supportedLocales = (List<String>) context.getAttribute("supportedLocales");
        this.defaultLocale = (String) context.getAttribute("defaultLocale");
    }


    @Override
    public void destroy() {

    }

    private Locale getLocaleFromRequest(ServletRequest servletRequest) {
        Enumeration<Locale> locales = servletRequest.getLocales();
        while (locales.hasMoreElements()) {
            Locale locale = locales.nextElement();
            if (supportedLocales.contains(locale.getLanguage())) {
                return locale;
            }
        }
        return new Locale(defaultLocale);
    }

    private Locale getLocaleFromRequestParam(ServletRequest servletRequest) {
        String lang = servletRequest.getParameter("lang");
        return (supportedLocales.contains(lang)) ? new Locale(lang) : new Locale(defaultLocale);
    }

    private boolean checkLangIsPresent(ServletRequest request) {
        String lang = request.getParameter("lang");
        return validateStringIsNotNullOrEmpty(lang);
    }


    public static boolean validateStringIsNotNullOrEmpty(String string) {
        return string != null && !string.isEmpty();
    }

}
