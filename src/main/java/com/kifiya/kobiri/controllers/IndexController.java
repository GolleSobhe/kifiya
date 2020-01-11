package com.kifiya.kobiri.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

    @RequestMapping(value = {"/", "index", "acceuil"}, method = RequestMethod.GET)
    public String index(Model model){
        model.addAttribute("connexion", "connexion");
        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String indexConnected(Model model){
        model.addAttribute("connexion", "deconnexion");
        return "index";
    }

}
