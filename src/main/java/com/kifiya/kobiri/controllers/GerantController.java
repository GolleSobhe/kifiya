package com.kifiya.kobiri.controllers;

import com.kifiya.kobiri.models.Gerant;
import com.kifiya.kobiri.services.GerantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class GerantController {

    @Autowired
    GerantService gerantService;

    @GetMapping(value = "/gerant")
    public String connexion(){
        return "gerant/signIn";
    }

    @GetMapping(value = "/gerant/new")
    public String ajouterGerant(Model model){
        model.addAttribute("gerant",new Gerant());
        return "gerant/gerantForm";
    }

    @PostMapping("/gerant")
    public String sauverGerant(@Valid Gerant gerant, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "gerant/gerantForm";
        }
        gerantService.ajouterGerant(gerant);
        return "index";
    }

}
