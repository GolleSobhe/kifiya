package com.kifiya.kobiri.controllers;

import com.kifiya.kobiri.models.Gerant;
import com.kifiya.kobiri.services.GerantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("/gerant")
public class GerantController {

    @Autowired
    GerantService gerantService;

    @RequestMapping(value ={"/", ""}, method = RequestMethod.GET)
    public String acceuil(Model model){
        model.addAttribute("statistique", gerantService.obtenirStatistique());
        return "gerant/index";
    }


    @RequestMapping(value ="/connexion", method = RequestMethod.GET)
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
        gerantService.ajouter(gerant);
        return "index";
    }

    /*@RequestMapping(value = "/transferts", method = RequestMethod.GET)
    public String rechercherTransferts(@RequestParam(value = "search", required = false) String code, Model model){
        model.addAttribute("transferts", gerantService.rechercherTransfert(code));
        return "gerant/gestionTransfert";
    }

    @RequestMapping(value = "transferts", method = RequestMethod.POST)
    public String validerTransferts(@RequestParam(value = "search", required = false) String code,
                                    @Valid @ModelAttribute("transfert") Transfert transfert,
                                    BindingResult bindingResult, Model model){
        if(!bindingResult.hasErrors()) {
            gerantService.validerTransfert(transfert);
            model.addAttribute("transferts", gerantService.rechercherTransfert(code));
            return "gerant/gestionTransfert";
        }
        return "gerant/gestionTransfert";
    }*/

    @RequestMapping(value = "/historique", method = RequestMethod.GET)
    public String historique(Model model){
        model.addAttribute("transferts", gerantService.rechercherTransfert());
        return "gerant/historique";
    }
}
