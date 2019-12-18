package entity;

import java.io.Serializable;
import java.util.Objects;

public class Book implements Serializable {

    private static final long serialVersionUID = -5607439182395666167L;

    private int id;
    private String title;
    private String author;
    private String genre;
    private String edition;
    private int typeId;
    private String image;
    private double price;

    public Book() {

    }

    public Book(int id, String title, String author, String genre, String edition, int typeId, String image, double price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.edition = edition;
        this.typeId = typeId;
        this.image = image;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id &&
                typeId == book.typeId &&
                Objects.equals(title, book.title) &&
                Objects.equals(author, book.author) &&
                Objects.equals(genre, book.genre) &&
                Objects.equals(edition, book.edition) &&
                Objects.equals(image, book.image) &&
                Objects.equals(price, book.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, genre, edition, typeId, image, price);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", edition='" + edition + '\'' +
                ", typeId=" + typeId +
                ", image='" + image + '\'' +
                ", price=" + price +
                '}';
    }
}
