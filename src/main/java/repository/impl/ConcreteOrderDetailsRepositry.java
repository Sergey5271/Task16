package repository.impl;

import bean.OrderDetailsBean;
import db.mysql.MysqlDataSource;
import repository.OrderDetailsRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConcreteOrderDetailsRepositry implements OrderDetailsRepository {

    private static final int COLUMN_TITLE = 1;
    private static final int COLUMN_STATUS = 2;
    private static final int COLUMN_COUNT = 3;
    private static final int COLUMN_PRICE = 4;

    private static final Logger LOGGER = Logger.getLogger(ConcreteOrderDetailsRepositry.class.getName());


    @Override
    public List<OrderDetailsBean> findAllBeans(Connection connection) {
        List<OrderDetailsBean> orderDetailsBeans = new ArrayList<>();
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = MysqlDataSource.getConnection();
            preparedStatement = connection.prepareStatement("SELECT books.title, order.status, books_has_order.count, books_has_order.price FROM books JOIN books_has_order ON books_id = books_has_order.books_id JOIN `order` ON books_has_order.order_id = order_id");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                orderDetailsBeans.add(createOrderFromResultBean(resultSet));
            }
            return orderDetailsBeans;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "SQL connection is missing", e);
        } finally {
            MysqlDataSource.close(connection, preparedStatement, resultSet);
        }
        return null;
    }


    private OrderDetailsBean createOrderFromResultBean(ResultSet resultSet) throws SQLException {
        String title = resultSet.getString(COLUMN_TITLE);
        String status = resultSet.getString(COLUMN_STATUS);
        String count = resultSet.getString(COLUMN_COUNT);
        String price = resultSet.getString(COLUMN_PRICE);
        return new OrderDetailsBean(title, status, count, price);
    }
}
