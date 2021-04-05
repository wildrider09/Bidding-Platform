package com.nitesh.biddingPlatform.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.nitesh.biddingPlatform.model.Product;
import com.nitesh.biddingPlatform.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "product")
public class ProductResource {
    @Autowired
    private ProductService productService;

    @PostMapping
    public Product addProduct(@RequestBody JsonNode jsonNode) {

        //return null;
        Product product = productService.addProduct(jsonNode);
        try {
            return product.shallowCopy();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
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
    return response;
    }

    @GetMapping(value = "{productId}")
    public Product getProduct(@PathVariable int productId) {
        Product product =  productService.getProduct(productId);
        try {
            return product.shallowCopy();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PutMapping(value = "{productId}")
    public Product updateProduct(@PathVariable int productId, @RequestBody JsonNode jsonNode){
        Product product = productService.updateProduct(productId, jsonNode);
        try {
            return product.shallowCopy();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/{productId}",method=RequestMethod.DELETE)
    public @ResponseBody void deleteProduct(@PathVariable int productId){
        productService.deleteProduct(productId);
    }


}
