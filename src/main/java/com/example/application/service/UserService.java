package com.example.application.service;

import com.example.application.entity.User;
import com.example.application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.HashMap;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    //get current user points
//    public int userPoints(Principal principal) {
//        int points;
//
//        points = this.userRepository.findPointsByUserId(principal.getName());
//
//        return points;
//    }

    public User getUser(Principal principal) {

        User user = this.userRepository.findByUserId(principal.getName());

        return user;
    }

    //check if user exist
    public boolean userExist(Principal principal) {
        boolean exist = false;

        //User user = new User();

        if(this.userRepository.findByUserId(principal.getName()) != null ) {
            exist = true;
        }

        return exist;
    }

    //create new user
    public void createNewUser(Principal principal) {

        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        HashMap data = (HashMap) ((OAuth2Authentication) a).getUserAuthentication().getDetails();
        System.out.println(data.get("name").toString());

        User user = new User(principal.getName(), 0, data.get("name").toString());

        this.userRepository.save(user);
    }


    //update points of user

}
