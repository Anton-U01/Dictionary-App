package com.dictionaryapp.controller;

import com.dictionaryapp.service.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController{
    private CurrentUser currentUser;

    public HomeController(CurrentUser currentUser) {
        this.currentUser = currentUser;
    }

    @GetMapping("/home")
    public String logged(){
        if(!currentUser.isLoggedIn()){
            return "redirect:/";
        }
        return "home";
    }
    @GetMapping("/")
    public String notLogged(){
        if(currentUser.isLoggedIn()){
            return "redirect:/home";
        }
        return "index";
    }
}
