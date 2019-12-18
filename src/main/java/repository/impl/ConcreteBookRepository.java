package repository.impl;

import bean.ViewBookBean;
import db.mysql.MysqlDataSource;
import entity.Book;
import filter.BookFormFilter;
import repository.BookRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConcreteBookRepository implements BookRepository {

    private final static int COLUMN_ID = 1;
    private final static int COLUMN_TITLE = 2;
    private final static int COLUMN_AUTHOR = 3;
    private final static int COLUMN_GENRE = 4;
    private final static int COLUMN_EDITION = 5;
    private final static int COLUMN_TYPES_ID = 6;
    private final static int COLUMN_IMAGE = 7;
    private final static int COLUMN_PRICE = 8;

    private static final Logger LOGGER = Logger.getLogger(ConcreteBookRepository.class.getName());
    private static ConcreteBookRepository BOOK_REPOSITORY = new ConcreteBookRepository();

    public ConcreteBookRepository() {
    }

    public static ConcreteBookRepository getInstance() {
        return BOOK_REPOSITORY;
    }

    @Override
    public boolean add(Connection connection, Book book) {
        PreparedStatement preparedStatement = null;
        try {
            connection = MysqlDataSource.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO user (name, surname, email, password, image,price) VALUES (?,?,?,?,?,?)");
            preparedStatement.setString(COLUMN_TITLE - 1, book.getTitle());
            preparedStatement.setString(COLUMN_AUTHOR - 1, book.getAuthor());
            preparedStatement.setString(COLUMN_GENRE - 1, book.getGenre());
            preparedStatement.setString(COLUMN_EDITION - 1, book.getEdition());
            preparedStatement.setInt(COLUMN_TYPES_ID - 1, book.getTypeId());
            preparedStatement.setString(COLUMN_IMAGE - 1, book.getImage());
            preparedStatement.setDouble(COLUMN_PRICE - 1, book.getPrice());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "SQL connection is missing", e);
        } finally {
            MysqlDataSource.close(connection, preparedStatement);
        }
        return false;
    }

    @Override
    public Book get(Connection connection, int key) {
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = MysqlDataSource.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM books WHERE id = ?");
            preparedStatement.setInt(1, key);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return createBookFromResult(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "SQL connection is missing", e);
        } finally {
            MysqlDataSource.close(connection, preparedStatement, resultSet);
        }
        return null;
    }

    @Override
    public Book getByTitle(Connection connection, String title) {
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = MysqlDataSource.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM books WHERE title=?");
            preparedStatement.setString(1, title);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return createBookFromResult(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "SQL connection is missing", e);
        } finally {
            MysqlDataSource.close(connection, preparedStatement, resultSet);
        }
        return null;
    }

    @Override
    public List<ViewBookBean> findAllBeans(Connection connection) {
        List<ViewBookBean> viewBookBeans = new ArrayList<>();
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = MysqlDataSource.getConnection();
            preparedStatement = connection.prepareStatement("SELECT books.id, title, author,genre,edition,name,image,price FROM books INNER JOIN types ON books.types_id = types.id;");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                viewBookBeans.add(createBookFromResultBean(resultSet));
            }
            return viewBookBeans;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "SQL connection is missing", e);
        } finally {
            MysqlDataSource.close(connection, preparedStatement, resultSet);
        }
        return null;
    }

    @Override
    public List<ViewBookBean> findAllBeansFilter(Connection connection, BookFormFilter bookFormFilter) {
        List<ViewBookBean> viewBookBeans = new ArrayList<>();
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = MysqlDataSource.getConnection();
            preparedStatement = connection.prepareStatement(bookFormFilter.buildQuery());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                viewBookBeans.add(createBookFromResultBean(resultSet));
            }
            return viewBookBeans;
        }catch (SQLException e){
            LOGGER.log(Level.SEVERE, "SQL connection is missing", e);
        }finally {
            MysqlDataSource.close(connection, preparedStatement, resultSet);
        }
        return null;
    }

    @Override
    public List<Book> findAll(Connection connection) {
        List<Book> result = new ArrayList<>();
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = MysqlDataSource.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM books");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.add(createBookFromResult(resultSet));
            }
            return result;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "SQL connection is missing", e);
        } finally {
            MysqlDataSource.close(connection, preparedStatement, resultSet);
        }
        return null;
    }

    private ViewBookBean createBookFromResultBean(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(COLUMN_ID);
        String title = resultSet.getString(COLUMN_TITLE);
        String author = resultSet.getString(COLUMN_AUTHOR);
        String genre = resultSet.getString(COLUMN_GENRE);
        String edition = resultSet.getString(COLUMN_EDITION);
        String name = resultSet.getString(COLUMN_TYPES_ID);
        String image = resultSet.getString(COLUMN_IMAGE);
        String price = resultSet.getString(COLUMN_PRICE);
        return new ViewBookBean(id, title, author, genre, edition, name, image, price);
    }

    private Book createBookFromResult(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(COLUMN_ID);
        String title = resultSet.getString(COLUMN_TITLE);
        String author = resultSet.getString(COLUMN_AUTHOR);
        String genre = resultSet.getString(COLUMN_GENRE);
        String edition = resultSet.getString(COLUMN_EDITION);
        int columnId = resultSet.getInt(COLUMN_TYPES_ID);
        String image = resultSet.getString(COLUMN_IMAGE);
        double price = resultSet.getDouble(COLUMN_PRICE);
        return new Book(id, title, author, genre, edition, columnId, image,price);
    }
}
