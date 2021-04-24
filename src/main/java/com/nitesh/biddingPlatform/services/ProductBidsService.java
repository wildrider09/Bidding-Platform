package com.nitesh.biddingPlatform.services;
import com.fasterxml.jackson.databind.JsonNode;
import com.nitesh.biddingPlatform.dao.ProductBidsDao;
import com.nitesh.biddingPlatform.dao.ProductDao;
import com.nitesh.biddingPlatform.dao.UserDao;
import com.nitesh.biddingPlatform.exceptions.InvalidEntryException;
import com.nitesh.biddingPlatform.exceptions.ResourceNotFoundException;
import com.nitesh.biddingPlatform.model.Product;
import com.nitesh.biddingPlatform.model.ProductBids;
import com.nitesh.biddingPlatform.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public class ProductBidsService {
    @Autowired
    private ProductBidsDao bidDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private UserDao userDao;

    public ProductBids addBid(JsonNode jsonNode){
        ProductBids bid = new ProductBids();
        bid.setBidAmount(jsonNode.get("bidAmount").asInt());
        bid.setSelected(jsonNode.get("selected").asBoolean());
        //to get the product object
        int productId = jsonNode.get("bidProductId").asInt();
        Optional<Product> optionalProduct = productDao.findById(productId);
        if(optionalProduct.isEmpty()){
            throw new ResourceNotFoundException("Product with such product ID does not exist");
        }
        Product prod = optionalProduct.get();
        //to get the user object
        int userId = jsonNode.get("bidOwnerId").asInt();
        Optional<User> optionalUser = userDao.findById(userId);
        if(optionalUser.isEmpty()){
            throw new ResourceNotFoundException("User with such user ID does not exist");
        }
        User bidder = optionalUser.get();
        if(prod.getUser().getId() == userId){
            throw new InvalidEntryException("Can not bid on it's own product");
        }
        if(prod.getMinimum_bid() > bid.getBidAmount()){
            throw new InvalidEntryException("Can not bid with amount less than minimum bid");
        }
        bid.setProductToBid(prod);
        bid.setBidOwner(bidder);
        return bidDao.save(bid);
    }

    public List<ProductBids> getBids(){
        return bidDao.findAll();
    }

    public ProductBids getBid(int bidId){
        Optional<ProductBids> optionalProductBid = bidDao
                .findById(bidId);
        if(optionalProductBid.isEmpty()){
            throw new ResourceNotFoundException("Bid Id does not exist");
        }
        return optionalProductBid.get();
    }

    public ProductBids updateBid(int bidId, JsonNode jsonNode){
        ProductBids bid = new ProductBids();
        bid.setBidId(bidId);
        bid.setBidAmount(jsonNode.get("bidAmount").asInt());
        bid.setSelected(jsonNode.get("selected").asBoolean());
        //to get the product object
        int productId = jsonNode.get("bidProductId").asInt();
        Optional<Product> optionalProduct = productDao.findById(productId);
        if(optionalProduct.isEmpty()){
            throw new ResourceNotFoundException("Product with such product ID does not exist");
        }
        Product prod = optionalProduct.get();
        //to get the user object
        int userId = jsonNode.get("bidOwnerId").asInt();
        Optional<User> optionalUser = userDao.findById(userId);
        if(optionalUser.isEmpty()){
            throw new ResourceNotFoundException("User with such user ID does not exist");
        }
        User bidder = optionalUser.get();
        if(prod.getUser().getId() == userId){
            throw new InvalidEntryException("Can not bid on it's own product");
        }
        if(prod.getMinimum_bid() > bid.getBidAmount()){
            throw new InvalidEntryException("Can not bid with amount less than minimum bid");
        }
        bid.setProductToBid(prod);
        bid.setBidOwner(bidder);
        return bidDao.save(bid);
    }

    public void deleteBid(int bidId){
        try{
            bidDao.deleteById(bidId);
        }
        catch (ResourceNotFoundException ex){
            ex.printStackTrace();
        }
    }

    public List<ProductBids> getBidsByProductId(int productId)
    {
        return bidDao.getBidsByProductId(productId);
    }


}
