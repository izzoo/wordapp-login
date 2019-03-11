package com.example.application.controller;

import com.example.application.entity.Question;
import com.example.application.entity.User;
import com.example.application.service.QuestionService;
import com.example.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.HashMap;


@RestController
public class UserController {

    private UserService userService;
    private QuestionService questionService;

    @Autowired
    public UserController(UserService userService, QuestionService questionService) {
        this.userService = userService;
        this.questionService = questionService;
    }

    @RequestMapping("/user")
    public Principal user(Principal principal) {



        System.out.println(principal.toString());
        System.out.println("IN UserController.................................");

//        if(!this.userService.userExist(principal)) {
//            System.out.println("in UserService create user if!!!!!!!1");
//            this.userService.createNewUser(principal);
//        }

        return principal;
    }

    @RequestMapping("/points")
    public User us(Principal principal) {

        User user = this.userService.getUser(principal);
        //System.out.println(user.getPoints());
        return user;
    }

    @RequestMapping("/maskedanswer")
    public String getmask() {

        String masked = this.questionService.getMaskedAnswerService();

        return masked;
    }

    @RequestMapping("/question")
    public String getquestion() {

        String question = this.questionService.getQuestionService();

        return question;
    }
}
