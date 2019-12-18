package repository;

import entity.Order;

import java.sql.Connection;

public interface RepositoryOrder {

    void add(Connection connection, Order order);

    Order get(Connection connection, int id);

}
