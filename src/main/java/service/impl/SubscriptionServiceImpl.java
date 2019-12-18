package service.impl;

import db.mysql.MysqlDataSource;
import entity.Subscription;
import repository.SubscriptionRepository;
import repository.impl.ConcreteFactoryRepository;
import service.SubscriptionsService;
import servlet.CaptchaServlet;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SubscriptionServiceImpl implements SubscriptionsService {
    public static final Logger LOGGER = Logger.getLogger(CaptchaServlet.class.getName());


    private final static SubscriptionRepository SUBSCRIPTION_REPOSITORY = ConcreteFactoryRepository.getInstance().getSubscriptionRepository();

    private MysqlDataSource mysqlDataSource;

    public SubscriptionServiceImpl(MysqlDataSource mysqlDataSource) {
        this.mysqlDataSource = mysqlDataSource;
    }

    @Override
    public boolean add(Subscription subscription) {
        Connection connection = null;
        try {
            connection = MysqlDataSource.getConnection();
            return SUBSCRIPTION_REPOSITORY.add(connection, subscription);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error ", e);
        } finally {
            MysqlDataSource.close(connection);
        }
        return false;
    }
}
