package com.kifiya.kobiri.controllers;


import com.kifiya.kobiri.models.Boutique;
import com.kifiya.kobiri.services.BoutiqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class BoutiqueController {
    @Autowired
    private BoutiqueService boutiqueService;

    @RequestMapping(value = "boutiques", method = RequestMethod.GET)
    public String getBoutiques(Model model){
        model.addAttribute("boutiques", boutiqueService.findAll());
        model.addAttribute("boutique",new Boutique());
        return "boutique/boutique";
    }

    @RequestMapping(value = "boutiques", method = RequestMethod.POST)
    public String ajouterBoutique(@Valid @ModelAttribute("boutique") Boutique boutique,
                              BindingResult result, HttpServletRequest request, Model model){
        if (result.hasErrors()) {
            //AJouter le modele error de saissie
            return "boutique/boutique";
        }
        //assert
        boutiqueService.save(boutique);
        model.addAttribute("boutiques", boutiqueService.findAll());
        model.addAttribute("boutique",new Boutique());
        return "boutique/boutique";
    }
}
