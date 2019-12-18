package entity;

public class OrderDetails {

    private final int booksId;// cart
    private final int orderID;//order
    private final int count;//cart
    private final double price;//cart

    public OrderDetails(int booksId, int orderID, int count, double price) {
        this.booksId = booksId;
        this.orderID = orderID;
        this.count = count;
        this.price = price;
    }

    public int getBooksId() {
        return booksId;
    }

    public int getOrderID() {
        return orderID;
    }

    public int getCount() {
        return count;
    }

    public double getPrice() {
        return price;
    }
}
