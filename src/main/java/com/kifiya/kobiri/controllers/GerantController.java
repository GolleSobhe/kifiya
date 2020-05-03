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

    @RequestMapping(value = "gerant/transferts", method = RequestMethod.GET)
    public String rechercherTransferts(@RequestParam(value = "search", required = false) String code, Model model){
        model.addAttribute("transferts", gerantService.rechercherTransfert(code));
        return "gerant/gestionTransfert";
    }

    @RequestMapping(value = "gerant/transferts", method = RequestMethod.POST)
    public String validerTransferts(@RequestParam(value = "search", required = false) String code, @Valid @ModelAttribute("transfert") Transfert transfert,
                                    BindingResult result, Model model){
        gerantService.validerTransfert(transfert);
        model.addAttribute("transferts", gerantService.rechercherTransfert(code));
        return "gerant/gestionTransfert";
    }
}
