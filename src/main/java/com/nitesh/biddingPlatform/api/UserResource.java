package com.nitesh.biddingPlatform.api;

import com.nitesh.biddingPlatform.model.User;
import com.nitesh.biddingPlatform.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "user")
public class UserResource {
    @Autowired
    private UserService userService;

    @PostMapping
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping
    public List<User> getUsers() {
        List<User> users = userService.getUsers();
        List<User> response = new ArrayList<>();
        for (User user : users) {
            try {
                response.add(user.shallowCopy());
            } catch (CloneNotSupportedException e) {

            }
        }
        return response;
    }

    @GetMapping(value = "{userId}")
    public User getUser(@PathVariable int userId) {
        User user = userService.getUser(userId);
        try {
            return user.shallowCopy();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PutMapping(value = "{userId}")
    public User updateUser(@PathVariable int userId, @RequestBody User user){
        User updateduser = userService.updateuser(userId, user);
        try {
            return updateduser.shallowCopy();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/{userId}",method=RequestMethod.DELETE)
    public @ResponseBody void deleteUser(@PathVariable int userId){
        userService.deleteUser(userId);
    }
}
