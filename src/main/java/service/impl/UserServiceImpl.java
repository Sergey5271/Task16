package service.impl;

import db.mysql.MysqlDataSource;
import entity.User;
import repository.UserRepository;
import service.UserService;
import transaction.TransactionManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserServiceImpl implements UserService {

    public static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class.getName());


    private TransactionManager transactionManager;

    private UserRepository userRepository;

    public UserServiceImpl(TransactionManager transactionManager, UserRepository userRepository) {
        this.transactionManager = transactionManager;
        this.userRepository = userRepository;
    }

    @Override
    public boolean add(User user) {
        Connection connection = null;
        try {
            connection = MysqlDataSource.getConnection();
            transactionManager.doInTransaction(connection, con -> userRepository.add(con, user));
            return true;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error ", e);
        } finally {
            MysqlDataSource.close(connection);
        }
        return false;
    }

    @Override
    public User getByEmail(String email) {
        Connection connection = null;
        try {
            connection = MysqlDataSource.getConnection();
            return userRepository.getByEmail(connection, email);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error ", e);
        } finally {
            MysqlDataSource.close(connection);
        }
        return null;
    }
}
