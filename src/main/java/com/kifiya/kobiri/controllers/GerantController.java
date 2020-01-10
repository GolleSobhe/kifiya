package com.kifiya.kobiri.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GerantController {

    @GetMapping(value = "/gerant")
    public String connexion(){
        return "gerant/signIn";
    }

}
