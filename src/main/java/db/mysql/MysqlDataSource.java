package db.mysql;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MysqlDataSource {

    private MysqlDataSource() {
        throw new IllegalStateException("Utility class");
    }

    private static final Logger LOGGER = Logger.getLogger(MysqlDataSource.class.getName());

    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/bookshop_db");
            connection = ds.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        } catch (NamingException e) {
            LOGGER.log(Level.SEVERE, "No database connection", e);
        } finally {
            close(connection);
        }
        return connection;
    }

    public static void close(Connection connection, Statement statement, ResultSet resultSet) {
        close(connection, statement);
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "No database connection", e);
        }
    }

    public static void close(Connection connection, Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "No database connection", e);
        }
    }

    public static void close(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "No database connection", e);
        }
    }
}
