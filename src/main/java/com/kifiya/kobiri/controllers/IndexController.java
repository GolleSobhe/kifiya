package com.kifiya.kobiri.controllers;

import com.kifiya.kobiri.models.Transfert;
import com.kifiya.kobiri.services.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
public class IndexController {

    @Autowired
    private IndexService indexService;

    @RequestMapping(value = {"/", "index", "acceuil"}, method = RequestMethod.GET)
    public String index(Model model){
        model.addAttribute("parametre", indexService.obtenirPrametre());
        return "index";
    }

    @RequestMapping(value = {"/service-non-disponible"}, method = RequestMethod.GET)
    public String payement(){
        return "erreur/payement-facture";
    }


}
