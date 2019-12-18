package repository.impl;

import db.mysql.MysqlDataSource;
import entity.Role;
import entity.User;
import repository.UserRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConcreteUserRepository implements UserRepository {

    private final static int COLUMN_ID = 1;
    private final static int COLUMN_NAME = 2;
    private final static int COLUMN_SECOND_NAME = 3;
    private final static int COLUMN_EMAIL = 4;
    private final static int COLUMN_PASSWORD = 5;
    private final static int COLUMN_IMAGE = 6;
    private final static int COLUMN_ROLE = 7;

    private static final Logger LOGGER = Logger.getLogger(MysqlDataSource.class.getName());
    private static ConcreteUserRepository USER_REPOSITORY = new ConcreteUserRepository();

    public ConcreteUserRepository() {

    }

    public static ConcreteUserRepository getInstance() {
        return USER_REPOSITORY;
    }

    @Override
    public boolean add(Connection connection, User user) {
        PreparedStatement preparedStatement = null;
        try {
            connection = MysqlDataSource.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO user (name, surname, email, password, image, role) VALUES (?,?,?,?,?,?)");
            preparedStatement.setString(COLUMN_NAME - 1, user.getFirstName());
            preparedStatement.setString(COLUMN_SECOND_NAME - 1, user.getSecondName());
            preparedStatement.setString(COLUMN_EMAIL - 1, user.getEmailAddress());
            preparedStatement.setString(COLUMN_PASSWORD - 1, user.getPassword());
            preparedStatement.setString(COLUMN_IMAGE - 1, user.getImage());
            preparedStatement.setString(COLUMN_ROLE -1 , user.getRole().name());
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
    public User get(Connection connection, int key) {
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = MysqlDataSource.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE id=?");
            preparedStatement.setInt(1, key);
            resultSet = preparedStatement.executeQuery();
            return createUserFromResult(resultSet);
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "SQL connection is missing", ex);
        } finally {
            MysqlDataSource.close(connection, preparedStatement, resultSet);
        }
        return null;
    }

    @Override
    public User getByEmail(Connection connection, String email) {
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = MysqlDataSource.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE email=?");
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return createUserFromResult(resultSet);
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "SQL connection is missing", ex);
        } finally {
            MysqlDataSource.close(connection, preparedStatement, resultSet);
        }
        return null;
    }

    private User createUserFromResult(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(COLUMN_ID);
        String firstName = resultSet.getString(COLUMN_NAME);
        String secondName = resultSet.getString(COLUMN_SECOND_NAME);
        String email = resultSet.getString(COLUMN_EMAIL);
        String password = resultSet.getString(COLUMN_PASSWORD);
        String image = resultSet.getString(COLUMN_IMAGE);
        Role role = Role.valueOf(resultSet.getString(COLUMN_ROLE));
        return new User(id, firstName, secondName, email, password, image,role);
    }
}
