package com.kifiya.kobiri.controllers;

import com.kifiya.kobiri.models.Gerant;
import com.kifiya.kobiri.models.Transfert;
import com.kifiya.kobiri.services.GerantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;

@Controller
@RequestMapping("/gerant")
public class GerantController {

    @Autowired
    GerantService gerantService;

    @RequestMapping(method = RequestMethod.GET)
    public String acceuil(Model model){
        model.addAttribute("transferts", gerantService.rechercherTransfert());
        model.addAttribute("historiques", gerantService.rechercherTransfert());
        model.addAttribute("statistique", gerantService.obtenirStatistique());
        model.addAttribute("profil", new ArrayList<>());
        return "gerant/accueil-gerant";
    }

    @RequestMapping(value = "/gerant/new", method = RequestMethod.GET)
    public String ajouterGerant(Model model){
        model.addAttribute("gerant",new Gerant());
        return "gerant/gerantForm";
    }

    @RequestMapping(value = {"/gerant/", "/gerant"}, method = RequestMethod.POST)
    public String sauverGerant(@Valid Gerant gerant, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "gerant/gerantForm";
        }
        gerantService.ajouter(gerant);
        return "index";
    }

    @RequestMapping(value = "/gerant/transferts", method = RequestMethod.GET)
    public String rechercherTransferts(@RequestParam(value = "search", required = false) String code, Model model){
        model.addAttribute("transferts", gerantService.rechercherTransfert(code));
        return "gerant/accueil";
    }

    @RequestMapping(value = "/gerant/transferts", method = RequestMethod.POST)
    public String validerTransferts(@RequestParam(value = "search", required = false) String code,
                                    @Valid @ModelAttribute("transfert") Transfert transfert,
                                    BindingResult bindingResult, Model model){
        if(!bindingResult.hasErrors()) {
            gerantService.validerTransfert(transfert);
            model.addAttribute("transferts", gerantService.rechercherTransfert(code));
            return "gerant/accueil";
        }
        return "gerant/accueil";
    }

}
