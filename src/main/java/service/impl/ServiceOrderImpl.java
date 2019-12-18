package service.impl;

import db.mysql.MysqlDataSource;
import entity.Order;
import repository.RepositoryOrder;
import service.ServiceOrder;
import transaction.TransactionManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceOrderImpl implements ServiceOrder {

    public static final Logger LOGGER = Logger.getLogger(ServiceOrderImpl.class.getName());
    private RepositoryOrder repositoryOrder;
    private TransactionManager transactionManager;

    public ServiceOrderImpl(RepositoryOrder repositoryOrder, TransactionManager transactionManager) {
        this.repositoryOrder = repositoryOrder;
        this.transactionManager = transactionManager;
    }

    @Override
    public void add(Order order) {
        Connection connection = null;
        try {
            connection = MysqlDataSource.getConnection();
            transactionManager.doInTransaction(connection, con -> repositoryOrder.add(con, order));
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error ", e);
        } finally {
            MysqlDataSource.close(connection);
        }
    }

    @Override
    public void getOrder(int orderId) {
        Connection connection = null;
        try {
            connection = MysqlDataSource.getConnection();
            transactionManager.doInTransaction(connection, con -> repositoryOrder.get(con, orderId));
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error ", e);
        } finally {
            MysqlDataSource.close(connection);
        }

    }
}
