package bean;

public class OrderDetailsBean {

    private String title;
    private String status;
    private String count;
    private String price;

    public OrderDetailsBean(String title, String status, String count, String price) {
        this.title = title;
        this.status = status;
        this.count = count;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
