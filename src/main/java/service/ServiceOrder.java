package service;

import entity.Order;

public interface ServiceOrder {

    void add(Order order);

    void getOrder(int orderId);
}
