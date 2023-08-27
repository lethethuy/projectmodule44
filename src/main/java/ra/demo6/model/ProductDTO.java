package ra.demo6.model;

import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;

public class ProductDTO {
    private int id;
    private String productName;
    private MultipartFile imgUrl;
    private String description;
    private int stock;
    private int catalogId;
    private double price;
    private boolean status;

    public ProductDTO() {
    }

    public ProductDTO(int id, String productName, MultipartFile imgUrl, String description, int stock, int catalogId, double price, boolean status) {
        this.id = id;
        this.productName = productName;
        this.imgUrl = imgUrl;
        this.description = description;
        this.stock = stock;
        this.catalogId = catalogId;
        this.price = price;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public MultipartFile getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(MultipartFile imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
