package com.nitesh.biddingPlatform.services;

import com.nitesh.biddingPlatform.dao.UserDao;
import com.nitesh.biddingPlatform.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        return optionalUser.get();
    }

    public User updateuser(int userId, User user){
        user.setId(userId);
        return userDao.save(user);
    }

    public void deleteUser(int userId){
        userDao.deleteById(userId);
    }
}
