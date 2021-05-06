package com.nitesh.biddingPlatform.services;

import com.nitesh.biddingPlatform.dao.UserDao;
import com.nitesh.biddingPlatform.exceptions.ResourceNotFoundException;
import com.nitesh.biddingPlatform.model.Product;
import com.nitesh.biddingPlatform.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserDao userDao;

    public User addUser(User user){
        return userDao.save(user);
    }

    public List<User> getUsers(){
        return userDao.findAll();
    }

    public User getUser(int userId){
        Optional<User> optionalUser = userDao.findById(userId);
        if(!optionalUser.isPresent()){
            throw new ResourceNotFoundException("User Id does not exist");
        }
        return optionalUser.get();
    }

    public User updateuser(int userId, User user){
        if(!userDao.findById(userId).isPresent()){
            throw new ResourceNotFoundException("User Id does not exist");
        }
        user.setId(userId);
        return userDao.save(user);
    }

    public void deleteUser(int userId){
        try{
            userDao.deleteById(userId);
        }
        catch (ResourceNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public int testing(int userId){
        return 1;
    }


}
