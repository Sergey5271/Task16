package repository;

import entity.User;

import java.sql.Connection;

public interface UserRepository {

    boolean add(Connection connection, User user);

    User get(Connection connection, int key);

    User getByEmail(Connection connection, String email);
}
