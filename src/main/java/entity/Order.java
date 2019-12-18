package entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
    private int id;
    private String status;
    private String statusDescription;
    private Date date;
    private int userId;
    private String creditCard;
    private String address;
    private List<OrderDetails> orderDetails;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public Date getDate() {
        return (Date) date.clone();
    }

    public void setDate(Date date) {
        this.date = (Date) date.clone();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<OrderDetails> getOrderDetails() {
        return new ArrayList<>(orderDetails);
    }

    public void setOrderDetails(List<OrderDetails> orderDetails) {
        this.orderDetails = new ArrayList<>(orderDetails);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", statusDescription='" + statusDescription + '\'' +
                ", date=" + date +
                ", userId=" + userId +
                ", creditCard='" + creditCard + '\'' +
                ", address='" + address + '\'' +
                ", orderDetails=" + orderDetails +
                '}';
    }

}
