package com.nitesh.biddingPlatform.dao;

import com.nitesh.biddingPlatform.model.Product;
import com.nitesh.biddingPlatform.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {
    @Override
    List<User> findAll();


}
