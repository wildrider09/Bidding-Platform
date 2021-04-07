package com.nitesh.biddingPlatform.model;

import javax.persistence.*;

@Entity
public class ProductBids implements Cloneable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int bidId;
    private int bidAmount;
    private Boolean selected;
    @ManyToOne
    @JoinColumn(name = "bidProductId", nullable = false)
    private Product productToBid;
    @ManyToOne
    @JoinColumn(name = "bidOwnerId", nullable = false)
    private User bidOwner;

    public ProductBids(int bidId, int bidAmount, Boolean selected, Product productToBid, User bidOwner) {
        this.bidId = bidId;
        this.bidAmount = bidAmount;
        this.selected = selected;
        this.productToBid = productToBid;
        this.bidOwner = bidOwner;
    }

    public ProductBids() {
    }

    public int getBidId() {
        return bidId;
    }

    public void setBidId(int bidId) {
        this.bidId = bidId;
    }

    public int getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(int bidAmount) {
        this.bidAmount = bidAmount;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public Product getProductToBid() {
        return productToBid;
    }

    public void setProductToBid(Product productToBid) {
        this.productToBid = productToBid;
    }

    public User getBidOwner() {
        return bidOwner;
    }

    public void setBidOwner(User bidOwner) {
        this.bidOwner = bidOwner;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "ProductBids{" +
                "bidId=" + bidId +
                ", bidAmount=" + bidAmount +
                ", selected=" + selected +
                ", productToBid=" + null +
                ", bidOwner=" + null +
                '}';
    }

    public ProductBids shallowCopy() throws CloneNotSupportedException{
        ProductBids cloneBids = (ProductBids) this.clone();
        cloneBids.setBidOwner(null);
        cloneBids.setProductToBid(null);
        return cloneBids;
    }




}



