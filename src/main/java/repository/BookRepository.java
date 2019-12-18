package repository;

import bean.ViewBookBean;
import entity.Book;
import filter.BookFormFilter;

import java.sql.Connection;
import java.util.List;

public interface BookRepository {

    boolean add(Connection connection, Book book);

    Book get(Connection connection, int key);

    Book getByTitle(Connection connection, String title);

    List<ViewBookBean> findAllBeans(Connection connection);

    List<ViewBookBean> findAllBeansFilter(Connection connection, BookFormFilter bookFormFilter);

    List<Book> findAll(Connection connection);


}
