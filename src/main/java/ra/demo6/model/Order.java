package ra.demo6.model;

import java.util.Date;

public class Order {
    private int id;
    private int userId;
    private Date orderAt;
    private int totalPrice;
    private int phone;
    private String address;
    private String fullName;
    private int status;

    public Order(int id, int userId, Date orderAt, int totalPrice, int phone, String address, String fullName, int status) {
        this.id = id;
        this.userId = userId;
        this.orderAt = orderAt;
        this.totalPrice = totalPrice;
        this.phone = phone;
        this.address = address;
        this.fullName = fullName;
        this.status = status;
    }

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getOrderAt() {
        return orderAt;
    }

    public void setOrderAt(Date orderAt) {
        this.orderAt = orderAt;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int isStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getfullName() {
        return fullName;
    }

    public void setfullName(String fullName) {
        this.fullName = fullName;
    }
}
