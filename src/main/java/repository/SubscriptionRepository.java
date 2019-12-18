package repository;

import entity.Subscription;

import java.sql.Connection;

public interface SubscriptionRepository {

    boolean add(Connection connection, Subscription subscription);

    Subscription getById(Connection connection, int id);

    Subscription getByTitle(Connection connection, String title);
}
