package com.kifiya.kobiri.controllers;

import com.kifiya.kobiri.models.user.User;
import com.kifiya.kobiri.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "user/new")
    public String inscription(Model model){
        model.addAttribute("user",new User());
        return "user/register";
    }

    @PostMapping(value = "user")
    public String save(User user){
        this.userService.register(user);
        return "index";
    }

    @GetMapping(value = "user/signIn")
    public String connexion(){
        return "user/signIn";
    }

    @GetMapping(value = "user/contact-us")
    public String contactUs(){
        return "user/contactUs";
    }
}
