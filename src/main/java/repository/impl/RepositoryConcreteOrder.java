package repository.impl;

import db.mysql.MysqlDataSource;
import entity.Order;
import entity.OrderDetails;
import repository.RepositoryOrder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RepositoryConcreteOrder implements RepositoryOrder {

    private static final Logger LOGGER = Logger.getLogger(MysqlDataSource.class.getName());

    private static RepositoryConcreteOrder ORDER = new RepositoryConcreteOrder();

    public static RepositoryConcreteOrder getInstance() {
        return ORDER;
    }

    private static final String SQL_QUERY_INSERT_ORDER = "INSERT INTO `order` ( status, status_description, date, user_id, credit_card, address) VALUES (?,?,?,?,?,?)";
    private static final String SQL_QUERY_INSERT_ORDER_DETAILS = "INSERT INTO `books_has_order` (books_id, order_id, count, price) VALUES (?,?,?,?)";


    private static final int COLUMN_STATUS = 2;
    private static final int COLUMN_STATUS_DESCRIPTION = 3;
    private static final int COLUMN_DATE = 4;
    private static final int COLUMN_USER_ID = 5;
    private static final int COLUMN_CREDIT_CARD = 6;
    private static final int COLUMN_ADDRESS = 7;

    private static final int COLUMN_BOOKS_ID = 1;
    private static final int COLUMN_ORDER_ID = 2;
    private static final int COLUMN_COUNT = 3;
    private static final int COLUMN_PRICE = 4;

    @Override
    public void add(Connection connection, Order order) {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(SQL_QUERY_INSERT_ORDER, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(COLUMN_STATUS - 1, order.getStatus());
            preparedStatement.setString(COLUMN_STATUS_DESCRIPTION - 1, order.getStatusDescription());
            preparedStatement.setDate(COLUMN_DATE - 1, new java.sql.Date(order.getDate().getTime()));
            preparedStatement.setInt(COLUMN_USER_ID - 1, order.getUserId());
            preparedStatement.setString(COLUMN_CREDIT_CARD - 1, order.getCreditCard());
            preparedStatement.setString(COLUMN_ADDRESS - 1, order.getAddress());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                resultSet.next();
                int orderId = resultSet.getInt(1);
                order.setId(orderId);
                addOrderDetails(connection, order);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "SQL connection is missing", e);
        }
    }

    private void addOrderDetails(Connection connection, Order order) {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(SQL_QUERY_INSERT_ORDER_DETAILS);
            for (OrderDetails details : order.getOrderDetails()) {
                preparedStatement.setInt(COLUMN_BOOKS_ID, details.getBooksId());
                preparedStatement.setInt(COLUMN_ORDER_ID, order.getId());
                preparedStatement.setInt(COLUMN_COUNT, details.getCount());
                preparedStatement.setDouble(COLUMN_PRICE, details.getPrice());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "SQL connection is missing", e);
        }
    }

    @Override
    public Order get(Connection connection, int id) {
        return null;
    }
}
