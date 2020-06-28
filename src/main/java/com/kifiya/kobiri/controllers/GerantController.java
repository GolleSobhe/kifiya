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

    private final GerantService gerantService;

    public GerantController(GerantService gerantService) {
        this.gerantService = gerantService;
    }

    @GetMapping(value ={"/", ""})
    public String acceuil(Model model){
        model.addAttribute("statistique", gerantService.obtenirStatistique());
        return "gerant/index";
    }


    @GetMapping(value ="/connexion")
    public String connexion(){
        return "gerant/signIn";
    }

    @GetMapping(value = "/transferts")
    public String rechercherTransferts(@RequestParam(value = "search", required = false) String code, Model model){
        model.addAttribute("transferts", gerantService.rechercherTransfert(code));
        return "gerant/gestionTransfert";
    }

    @PostMapping(value = "transferts")
    public String validerTransferts(@RequestParam(value = "search", required = false) String code,
                                    @Valid @ModelAttribute("transfert") Transfert transfert,
                                    BindingResult bindingResult, Model model){
        if(!bindingResult.hasErrors()) {
            gerantService.validerTransfert(transfert);
            model.addAttribute("transferts", gerantService.rechercherTransfert(code));
            return "gerant/gestionTransfert";
        }
        return "gerant/gestionTransfert";
    }

    @RequestMapping(value = "/historique", method = RequestMethod.GET)
    public String historique(Model model){
        model.addAttribute("transferts", gerantService.rechercherTransfert());
        return "gerant/historique";
    }
}
