package com.nitesh.biddingPlatform.dao;

import com.nitesh.biddingPlatform.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductDao extends JpaRepository<Product, Integer> {
    @Override
    List<Product> findAll();

    @Query(value = "SELECT * FROM product where bidder_id = ?1", nativeQuery = true)
    List<Product> getProductsListByBidderId(int bidderId);
}
