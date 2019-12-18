package service.impl;

import bean.ViewBookBean;
import db.mysql.MysqlDataSource;
import entity.Book;
import filter.BookFormFilter;
import repository.BookRepository;
import service.BookService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookServiceImpl implements BookService {

    public static final Logger LOGGER = Logger.getLogger(BookServiceImpl.class.getName());

    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @Override
    public Book get(int key) {
        Connection connection = null;
        try {
            connection = MysqlDataSource.getConnection();
            return bookRepository.get(connection, key);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error ", e);
        } finally {
            MysqlDataSource.close(connection);
        }
        return null;
    }

    @Override
    public List<Book> findAll() {
        Connection connection = null;
        try {
            connection = MysqlDataSource.getConnection();
            return bookRepository.findAll(connection);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error ", e);
        } finally {
            MysqlDataSource.close(connection);
        }
        return null;
    }

    @Override
    public List<ViewBookBean> findAllBean() {
        Connection connection = null;
        try {
            connection = MysqlDataSource.getConnection();
            return bookRepository.findAllBeans(connection);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error ", e);
        } finally {
            MysqlDataSource.close(connection);
        }
        return null;
    }

    @Override
    public List<ViewBookBean> findAllBeansFilter(BookFormFilter bookFormFilter) {
        Connection connection = null;
        try {
            connection = MysqlDataSource.getConnection();
            return bookRepository.findAllBeansFilter(connection, bookFormFilter);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error ", e);
        } finally {
            MysqlDataSource.close(connection);
        }
        return null;
    }
}
