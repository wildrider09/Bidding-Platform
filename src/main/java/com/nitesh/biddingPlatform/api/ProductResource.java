package com.nitesh.biddingPlatform.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.nitesh.biddingPlatform.model.Product;
import com.nitesh.biddingPlatform.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "product")
public class ProductResource {

    private static final Logger logger = LoggerFactory.getLogger(ProductResource.class);

    @Autowired
    private ProductService productService;

    @PostMapping
    public Product addProduct(@RequestBody JsonNode jsonNode) {

        //return null;
        Product product = productService.addProduct(jsonNode);
        try {
            return(product.shallowCopy());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        logger.info("adding new product in sql database!!!");

        return null;
    }

    @GetMapping
    public List<Product> getProducts() {
    List<Product> products = productService.getProducts();
    List <Product> response = new ArrayList<>();
    for(Product product : products){
            try {
                response.add(product.shallowCopy());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        logger.info("getting list of products from sql database!!!");

        return response;
    }

    @GetMapping(value = "{productId}")
    public Product getProduct(@PathVariable int productId) {
        Product product =  productService.getProduct(productId);
        try {
            return(product.shallowCopy());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        logger.info("getting single product by productid from sql database!!!");

        return null;
    }

    @PutMapping(value = "{productId}")
    public Product updateProduct(@PathVariable int productId, @RequestBody JsonNode jsonNode){
        Product product = productService.updateProduct(productId, jsonNode);
        try {
            return(product.shallowCopy());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        logger.info("updating an existing product in sql database!!!");

        return null;
    }

    @RequestMapping(value = "/{productId}",method=RequestMethod.DELETE)
    public @ResponseBody void deleteProduct(@PathVariable int productId){
        productService.deleteProduct(productId);
    }

    @GetMapping(value = "/productList/{bidderId}")
    public List<Product> getProductsListByBidderId(@PathVariable int bidderId){
        List<Product> products = productService.getProductsByUserId(bidderId);
        List <Product> response = new ArrayList<>();
        for(Product product : products){
            try {
                response.add(product.shallowCopy());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        logger.info("deleting an existing product in sql database!!!");

        return response;
    }


}
