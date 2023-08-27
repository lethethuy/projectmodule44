package ra.demo6.model;

public class OrderDetail {
    private int productId;
    private double price;
    private int quantity;
    private int orderId;

    public OrderDetail(int productId, double price, int quantity, int orderId) {
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
        this.orderId = orderId;
    }

    public OrderDetail() {
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

}
