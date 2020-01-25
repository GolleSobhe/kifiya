package com.kifiya.kobiri.controllers;

import com.kifiya.kobiri.models.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

    @RequestMapping(value = {"/", "index", "acceuil"}, method = RequestMethod.GET)
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(Model model){
        model.addAttribute("User", new User());
        return "index";
    }

    @RequestMapping(value = "admin", method = RequestMethod.GET)
    public String admin() {
        return "admin/admin";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String error403(){
        return "error/403";
    }
}
