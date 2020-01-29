package com.kifiya.kobiri.controllers;

import com.kifiya.kobiri.models.user.User;
import com.kifiya.kobiri.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
        this.userService.save(user);
        return "index";
    }

    @RequestMapping(value = "registration", method = RequestMethod.POST)
    public String registration(@Valid @ModelAttribute("user") User user,
                               BindingResult result){

        User existing = userService.findUserByEmail(user.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }

        if (result.hasErrors()) {
            return "error";
        }

        userService.save(user);
        return "redirect:index";
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
