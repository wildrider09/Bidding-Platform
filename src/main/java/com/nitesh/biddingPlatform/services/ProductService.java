package com.nitesh.biddingPlatform.services;
import com.fasterxml.jackson.databind.JsonNode;
import com.nitesh.biddingPlatform.dao.ProductDao;
import com.nitesh.biddingPlatform.dao.UserDao;
import com.nitesh.biddingPlatform.exceptions.ResourceNotFoundException;
import com.nitesh.biddingPlatform.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public class ProductService {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private UserDao userDao;


    public Product addProduct(JsonNode jsonNode){
        //JsonNode -> Product object creation
        //Retrieve user obj from db
        //prod.set(user);
        //session.save(prod)

        Product product = new Product();
        product.setProductName(jsonNode.get("productName").asText());
        product.setMinimum_bid(jsonNode.get("minimum_bid").asInt());
        product.setDescription(jsonNode.get("description").asText());
        product.setActive(jsonNode.get("active").asBoolean());
        int userId = jsonNode.get("ownerId").asInt();
        return userDao.findById(userId).map(user -> {
            product.setUser(user);
            return productDao.save(product);
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + userId + " not found"));
    }

    public List<Product> getProducts(){
        return productDao.findAll();
    }

    public Product getProduct(int productId){
        Optional<Product> productOptional = productDao.findById(productId);
        if(productOptional.isEmpty()){
            throw new ResourceNotFoundException("Product Id does not exist");
        }
        return productOptional.get();
    }

    public Product updateProduct(int productId, JsonNode jsonNode){
        Product product = new Product();
        product.setProductId(productId);
        product.setProductName(jsonNode.get("productName").asText());
        product.setMinimum_bid(jsonNode.get("minimum_bid").asInt());
        product.setDescription(jsonNode.get("description").asText());
        product.setActive(jsonNode.get("active").asBoolean());
        int userId = jsonNode.get("ownerId").asInt();
        return userDao.findById(userId).map(user -> {
            product.setUser(user);
            return productDao.save(product);
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + userId + " not found"));
    }

    public void deleteProduct(int productId){
        productDao.deleteById(productId);
    }

    public List<Product> getProductsByUserId(int userId){
        return productDao.getProductsListByBidderId(userId);
    }


}
