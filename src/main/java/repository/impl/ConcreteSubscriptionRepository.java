package repository.impl;

import db.mysql.MysqlDataSource;
import entity.Subscription;
import repository.SubscriptionRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConcreteSubscriptionRepository implements SubscriptionRepository {

    private final static int COLUMN_ID = 1;
    private final static int COLUMN_TITLE = 2;

    private static final Logger LOGGER = Logger.getLogger(MysqlDataSource.class.getName());
    private static ConcreteSubscriptionRepository SUBSCRIPTION_REPOSITORY = new ConcreteSubscriptionRepository();

    public ConcreteSubscriptionRepository() {
    }

    public static ConcreteSubscriptionRepository getInstance() {
        return SUBSCRIPTION_REPOSITORY;
    }

    @Override
    public boolean add(Connection connection, Subscription subscription) {
        PreparedStatement preparedStatement = null;
        try {
            connection = MysqlDataSource.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO subscriptions(title) VALUES(?)");
            preparedStatement.setString(COLUMN_TITLE - 1, subscription.getTitle());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "SQL connection is missing", ex);
        } finally {
            MysqlDataSource.close(connection, preparedStatement);
        }
        return false;
    }

    @Override
    public Subscription getByTitle(Connection connection, String title) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = MysqlDataSource.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM subscriptions WHERE email=?");
            preparedStatement.setString(COLUMN_ID, title);
            resultSet = preparedStatement.executeQuery();
            return createSubscriptionFromResult(resultSet);
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "SQL connection is missing", ex);
        } finally {
            MysqlDataSource.close(connection, preparedStatement, resultSet);
        }
        return null;

    }

    @Override
    public Subscription getById(Connection connection, int id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = MysqlDataSource.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM subscriptions WHERE id= ? ");
            preparedStatement.setInt(COLUMN_ID, id);
            resultSet = preparedStatement.executeQuery();
            return createSubscriptionFromResult(resultSet);
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "SQL connection is missing", ex);
        } finally {
            MysqlDataSource.close(connection, preparedStatement, resultSet);
        }
        return null;
    }


    private Subscription createSubscriptionFromResult(ResultSet resultSet) throws SQLException {
        if (resultSet.isBeforeFirst()) resultSet.next();

        int id = resultSet.getInt(COLUMN_ID);
        String title = resultSet.getString(COLUMN_TITLE);

        return new Subscription(id, title);
    }
}
