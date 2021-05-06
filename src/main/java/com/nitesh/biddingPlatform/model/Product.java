package com.nitesh.biddingPlatform.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product implements Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int productId;
    private String productName;
    private int minimum_bid;
    private String description;
    private Boolean active;
    @ManyToOne
    @JoinColumn(name = "ownerId", nullable = false)
    private User productOwner;

    @OneToMany(mappedBy = "productToBid", cascade = CascadeType.ALL)
    List<ProductBids> bids = new ArrayList<>();


    public Product(int productId, String productName, int minimum_bid, String description, Boolean active, User user, List<ProductBids> bids) {
        this.productId = productId;
        this.productName = productName;
        this.minimum_bid = minimum_bid;
        this.description = description;
        this.active = active;
        this.productOwner = null;
        this.bids = bids;
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
        return productOwner;
    }

    public void setUser(User user) {
        this.productOwner = user;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }


    public List<ProductBids> getBids() {
        return bids;
    }

    public void setBids(List<ProductBids> bids) {
        this.bids = bids;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", minimum_bid=" + minimum_bid +
                ", description='" + description + '\'' +
                ", active=" + active +
                ", user=" + null +
                ", bids=" + bids +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Product shallowCopy() throws CloneNotSupportedException {
        Product cloneProduct = (Product) this.clone();
        User user = cloneProduct.getUser();
        user.setProducts(null);
        user.setBids(null);
        cloneProduct.setUser(user);
        List<ProductBids> bids = cloneProduct.getBids();
        for(ProductBids bid : bids){
            Product product = bid.getProductToBid();
            product.setUser(null);
            product.setBids(null);
            bid.setProductToBid(product);
            User bidOwner = bid.getBidOwner();
            bidOwner.setBids(null);
            bidOwner.setProducts(null);
            bid.setBidOwner(bidOwner);
        }
        cloneProduct.setBids(bids);
        return cloneProduct;
    }




}
