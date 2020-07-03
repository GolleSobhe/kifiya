package com.kifiya.kobiri.controllers;

import com.kifiya.kobiri.models.Beneficiaire;
import com.kifiya.kobiri.models.Transfert;
import com.kifiya.kobiri.services.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String acceuil(Model model){
        model.addAttribute("transfert", new Transfert());
        return "client/accueil-client";
    }

    @PostMapping(value =  "transferts")
    public String postHistoric(@Valid @ModelAttribute("transfert") Transfert transfert,
                               BindingResult result, Model model){


        /*if (result.hasErrors() || !result.hasFieldErrors("client") || !result.hasFieldErrors("beneficiaire")) {
            //Ajouter le message d'erreur sur le model
            return"transfert/transfert";
        }*/

     //   transfertService.ajouter(transfert);

        model.addAttribute("transfert", transfert);
        model.addAttribute("confirmationMessage", "Argent enoyé et un e-mail de confirmation a été envoyé à ");
        return "client/accueil-client";
    }

    @RequestMapping(value = "/beneficiaires", method = RequestMethod.GET)
    public String beneficiaire(Model model){
        model.addAttribute("beneficiaires", clientService.getBeneficiaires());
        model.addAttribute("beneficiaire", new Beneficiaire());
        return "client/beneficiaire";
    }

    @RequestMapping(value = "/beneficiaires", method = RequestMethod.POST)
    public String ajouterBeneficiaire(@Valid @ModelAttribute("beneficiaire") Beneficiaire beneficiaire, BindingResult bindingResult, Model model){
        model.addAttribute("transfert", new Transfert());
        return "client/accueil-client";
    }

}
