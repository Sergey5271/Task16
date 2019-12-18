package service.impl;

import bean.OrderDetailsBean;
import db.mysql.MysqlDataSource;
import repository.OrderDetailsRepository;
import service.OrderDetailsService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderDetailsServiceImpl implements OrderDetailsService {

    public static final Logger LOGGER = Logger.getLogger(OrderDetailsServiceImpl.class.getName());

    private OrderDetailsRepository orderDetailsRepository;

    public OrderDetailsServiceImpl(OrderDetailsRepository orderDetailsRepository) {
        this.orderDetailsRepository = orderDetailsRepository;
    }

    @Override
    public List<OrderDetailsBean> findAllBean() {
        Connection connection = null;
        try {
            connection = MysqlDataSource.getConnection();
            return orderDetailsRepository.findAllBeans(connection);
        }catch (SQLException e){
            LOGGER.log(Level.SEVERE, "Error ", e);
        }finally {
            MysqlDataSource.close(connection);
        }
        return null;
    }
}
