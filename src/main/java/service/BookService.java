package service;

import bean.ViewBookBean;
import entity.Book;
import filter.BookFormFilter;

import java.util.List;

public interface BookService {

    Book get(int key);

    List<Book> findAll();

    List<ViewBookBean> findAllBean();

    List<ViewBookBean> findAllBeansFilter(BookFormFilter bookFormFilter);
}
