package context;


import exception.XMLException;
import image.Image;
import locale.LocaleStrategy;
import locale.LocaleStrategyContainer;
import org.xml.sax.SAXException;
import parser.XMLParser;
import repository.BookRepository;
import repository.RepositoryOrder;
import repository.UserRepository;
import repository.impl.ConcreteFactoryRepository;
import security.SecurityProperties;
import security.permission.PermissionEvaluator;
import security.permission.impl.PermissionEvaluatorImpl;
import service.impl.BookServiceImpl;
import service.impl.CaptchaServiceImpl;
import service.impl.ServiceOrderImpl;
import service.impl.UserServiceImpl;
import strategy.CaptchaStrategyContainer;
import transaction.TransactionManager;
import transaction.impl.ConcreteTransactionManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ContextListener implements ServletContextListener {

    public static final Logger LOGGER = Logger.getLogger(ContextListener.class.getName());


    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext context = servletContextEvent.getServletContext();
        TransactionManager transactionManager = new ConcreteTransactionManager();

        context.setAttribute("captchaService", new CaptchaServiceImpl());
        String captchaStrategy = context.getInitParameter("captchaStrategy");
        CaptchaStrategyContainer captchaStrategyContainer = new CaptchaStrategyContainer();
        context.setAttribute("captchaStrategy", captchaStrategyContainer.get(captchaStrategy));

        LocaleStrategyContainer localeStrategyContainer = new LocaleStrategyContainer();
        String localeStrategy = context.getInitParameter("localeStrategy");
        LocaleStrategy localeStrategy1 = localeStrategyContainer.get(localeStrategy);
        context.setAttribute("localeStrategy", localeStrategy1);

        String s = context.getInitParameter("supportedLocales");
        List<String> supportedLocales = Arrays.asList(s.split(" "));
        context.setAttribute("supportedLocales", supportedLocales);

        UserRepository userRepository = ConcreteFactoryRepository.getInstance().getUserRepository();
        context.setAttribute("userService", new UserServiceImpl(transactionManager, userRepository));

        String imagePath = context.getInitParameter("avatarPath");
        Image image = new Image(Paths.get(imagePath));
        context.setAttribute("image", image);

        String imageBooksPath = context.getInitParameter("booksPath");
        Image imageBooks = new Image(Paths.get(imageBooksPath));
        context.setAttribute("imageBooks", imageBooks);

        BookRepository bookRepository = ConcreteFactoryRepository.getInstance().getBookRepository();
        context.setAttribute("bookService", new BookServiceImpl(bookRepository));

        RepositoryOrder order = ConcreteFactoryRepository.getInstance().getRepositoryOrder();
        context.setAttribute("orderService", new ServiceOrderImpl(order, transactionManager));

        File file = new File(context.getRealPath("WEB-INF/security.xml"));
        try {
            XMLParser parser = new XMLParser();
            SecurityProperties properties = parser.parse(file);
            PermissionEvaluator permissionEvaluator = new PermissionEvaluatorImpl(properties);
            context.setAttribute("permissionEvaluator", permissionEvaluator);
        } catch (XMLException | ParserConfigurationException | SAXException | IOException | JAXBException e) {
            LOGGER.log(Level.SEVERE, "SQL connection is missing", e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
