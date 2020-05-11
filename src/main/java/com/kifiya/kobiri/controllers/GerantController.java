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

@Controller
@RequestMapping("/gerant")
public class GerantController {

    @Autowired
    GerantService gerantService;

    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
    public String connexion(){
        return "gerant/signIn";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String ajouterGerant(Model model){
        model.addAttribute("gerant",new Gerant());
        return "gerant/gerantForm";
    }

    @RequestMapping(value = {"/", ""}, method = RequestMethod.POST)
    public String sauverGerant(@Valid Gerant gerant, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "gerant/gerantForm";
        }
        gerantService.ajouterGerant(gerant);
        return "index";
    }

    @RequestMapping(value = "/transferts", method = RequestMethod.GET)
    public String rechercherTransferts(@RequestParam(value = "search", required = false) String code, Model model){
        model.addAttribute("transferts", gerantService.rechercherTransfert(code));
        return "gerant/gestionTransfert";
    }

    @RequestMapping(value = "transferts", method = RequestMethod.POST)
    public String validerTransferts(@RequestParam(value = "search", required = false) String code, @Valid @ModelAttribute("transfert") Transfert transfert,
                                    BindingResult result, Model model){
        gerantService.validerTransfert(transfert);
        model.addAttribute("transferts", gerantService.rechercherTransfert(code));
        return "gerant/gestionTransfert";
    }
}
