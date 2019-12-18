package repository;

import bean.OrderDetailsBean;

import java.sql.Connection;
import java.util.List;

public interface OrderDetailsRepository {

    List<OrderDetailsBean> findAllBeans(Connection connection);
}
