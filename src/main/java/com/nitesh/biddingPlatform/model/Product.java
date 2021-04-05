package com.nitesh.biddingPlatform.model;

import javax.persistence.*;

@Entity
public class Product implements Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int productId;
    private String productName;
    private int minimum_bid;
    private String description;
    @ManyToOne
    @JoinColumn(name = "bidderId", nullable = false)
    private User user;

    public Product(int productId, String productName, int minimum_bid, String description, User user) {
        this.productId = productId;
        this.productName = productName;
        this.minimum_bid = minimum_bid;
        this.description = description;
        this.user = user;
    }

    public Product() {
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getMinimum_bid() {
        return minimum_bid;
    }

    public void setMinimum_bid(int minimum_bid) {
        this.minimum_bid = minimum_bid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", minimum_bid=" + minimum_bid +
                ", description='" + description + '\'' +
                ", user=" + null +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Product shallowCopy() throws CloneNotSupportedException {
        Product cloneProduct = (Product) this.clone();
        cloneProduct.setUser(null);
        return cloneProduct;
    }
}
