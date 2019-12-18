package bean;

public class ViewBookBean {

    private int id;
    private String title;
    private String author;
    private String genre;
    private String edition;
    private String type;
    private String image;
    private String price;

    public ViewBookBean(int id, String title, String author, String genre, String edition, String type, String image, String price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.edition = edition;
        this.type = type;
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

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
}
