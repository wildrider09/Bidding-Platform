package com.nitesh.biddingPlatform.dao;

import com.nitesh.biddingPlatform.model.ProductBids;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductBidsDao extends JpaRepository<ProductBids, Integer> {
    @Override
    List<ProductBids> findAll();
}
