package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository3;

    @Autowired
    BlogService blogService3;

    public void createUser(User user){

        userRepository3.save(user);
    }

    public void deleteUser(int userId){

        if(userRepository3.findById(userId).isPresent()){
            userRepository3.deleteById(userId);
        }
    }


    public void updateUser(User user){

        User currUser=userRepository3.findByUserName(user.getUserName());
        currUser.setUserName(user.getUserName());
        currUser.setFirstName(user.getFirstName());
        currUser.setLastName(user.getLastName());
        currUser.setPassword(user.getPassword());

        userRepository3.save(currUser);
    }

    public User findUserByUsername(String username){
        return userRepository3.findByUserName(username);
    }
}
