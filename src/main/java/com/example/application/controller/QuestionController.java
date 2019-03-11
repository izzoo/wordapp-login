package com.example.application.controller;

import com.example.application.bindingmodel.BindingModel;
import com.example.application.entity.GuessedWord;
import com.example.application.entity.User;
import com.example.application.service.QuestionService;
import com.example.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Controller
public class QuestionController {

   // private final QuestionRepository questionRepository;
    private final QuestionService questionService;
    private final UserService userService;

    @Autowired
    public QuestionController(QuestionService questionService, UserService userService) {
        this.questionService = questionService;
        this.userService = userService;

    }


    @GetMapping("/play")
    public ModelAndView index(ModelAndView mv, Principal principal) {

        //Question q = new Question();

        //Question questions;
        //String question = this.questionRepository.findById(1).get().getQuestion();

        System.out.println("in QUESTIONCONTROLLER---------");
        if(!this.userService.userExist(principal)) {
            System.out.println("in UserService create user if!!!!!!!1");
            this.userService.createNewUser(principal);
        }

       String maskedAns = this.questionService.getMaskedAnswerService();
       String question = this.questionService.getQuestionService();
       User user = this.userService.getUser(principal);

        mv.setViewName("play");


        mv.addObject("question", question);
        mv.addObject("usertable", user );
        mv.addObject("maskedAns", maskedAns);

        return mv;
    }


//    @PostMapping("/play")
//    public String guess(BindingModel bindingModel, Principal principal) {
//
//        //Map<String, String> answerByUser = new HashMap<>();
//        this.questionService.setAnswerByUser(principal.getName(), bindingModel.getGuessedAnswer());
//
//        System.out.println(bindingModel.getGuessedAnswer());
//        System.out.println(this.questionService.getAnswerByUser().keySet().toArray()[0].toString());
//
//        this.questionService.setGuessedAnswer(bindingModel.getGuessedAnswer());
//
//
//        return "redirect:/play";
//    }


    //test
    @RequestMapping(value = "/play", method = RequestMethod.POST, consumes = "application/json")
    public void saveUser(@RequestBody GuessedWord guessedWord, ModelAndView mv, Principal principal) {
        System.out.println(guessedWord.getTest());

        //Map<String, String> answerByUser = new HashMap<>();
        this.questionService.setAnswerByUser(principal.getName(), guessedWord.getTest());


        System.out.println(this.questionService.getAnswerByUser().keySet().toArray()[0].toString());

        this.questionService.setGuessedAnswer(guessedWord.getTest());

    }
}
