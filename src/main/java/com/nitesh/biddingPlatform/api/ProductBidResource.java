package com.nitesh.biddingPlatform.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.nitesh.biddingPlatform.exceptions.InvalidEntryException;
import com.nitesh.biddingPlatform.model.Product;
import com.nitesh.biddingPlatform.model.ProductBids;
import com.nitesh.biddingPlatform.services.ProductBidsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidAlgorithmParameterException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "bids")
public class ProductBidResource {
    @Autowired
    private ProductBidsService bidsService;

    @PostMapping
    public ProductBids addProductBids(@RequestBody JsonNode jsonNode){
        ProductBids bids = bidsService.addBid(jsonNode);
        try{
            return bids.shallowCopy();
        } catch (CloneNotSupportedException e){
            e.printStackTrace();
        } catch (InvalidEntryException e){
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping
    public List<ProductBids> getBids(){
        List<ProductBids> bids = bidsService.getBids();
        List<ProductBids> response = new ArrayList<>();
        for(ProductBids bid : bids){
            try{
                response.add(bid.shallowCopy());
            } catch (CloneNotSupportedException e){
                e.printStackTrace();
            }
        }
        return response;
    }

    @GetMapping(value = "{bidId}")
    public ProductBids getBid(@PathVariable int bidId){
        ProductBids bid = bidsService.getBid(bidId);
        try{
            return(bid.shallowCopy());
        } catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
        return null;
    }

    @PutMapping(value = "{bidId}")
    public ProductBids updateBid(@PathVariable int bidId, @RequestBody JsonNode jsonNode){
        ProductBids bid = bidsService.updateBid(bidId, jsonNode);
        try{
            return(bid.shallowCopy());
        } catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/{bidId}",method=RequestMethod.DELETE)
    public @ResponseBody void deleteProduct(@PathVariable int bidId){
        bidsService.deleteBid(bidId);
    }

    @GetMapping(value = "/product/{productId}")
    public List<ProductBids> getBidsByProductId(@PathVariable int productId){
        List<ProductBids> bids = bidsService.getBidsByProductId(productId);
        List<ProductBids> response = new ArrayList<>();
        for(ProductBids bid : bids){
            try{
                response.add(bid.shallowCopy());
            } catch (CloneNotSupportedException e){
                e.printStackTrace();
            }
        }
        return response;
    }

}
