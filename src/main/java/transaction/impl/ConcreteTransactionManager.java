package transaction.impl;

import db.mysql.MysqlDataSource;
import transaction.Operation;
import transaction.TransactionManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConcreteTransactionManager implements TransactionManager {

    private static final Logger LOGGER = Logger.getLogger(MysqlDataSource.class.getName());

    @Override
    public void doInTransaction(Connection connection, Operation operation) {
        try {
            operation.action(connection);
            connection.commit();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "SQL connection is missing", ex);
            try {
                connection.rollback();
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "SQL connection is missing", e);
            }
        }
    }
}
