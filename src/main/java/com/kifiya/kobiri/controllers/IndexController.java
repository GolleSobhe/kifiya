package com.kifiya.kobiri.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

    @RequestMapping(value = {"/", "index", "acceuil"}, method = RequestMethod.GET)
    public String index(){
        return "index";
    }

    @RequestMapping(value = {"/service-non-disponible"}, method = RequestMethod.GET)
    public String payement(){
        return "erreur/payement-facture";
    }


}
