package com.kifiya.kobiri.controllers;

import com.kifiya.kobiri.models.Beneficiaire;
import com.kifiya.kobiri.models.Boutique;
import com.kifiya.kobiri.models.Client;
import com.kifiya.kobiri.models.Transfert;
import com.kifiya.kobiri.services.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String acceuil(Model model){
        /**
         * recuperer le taux apres
         */
        model.addAttribute("taux", 10600);
        return "client/accueil-client";
    }

    @RequestMapping(value = {"/transferts"}, method = RequestMethod.POST)
    public String initierTransfert(@RequestParam(value = "montantEuros") Long montant,
                                   @RequestParam(value = "pointDeRetrait") String pointDeRetrait,
                                   @RequestParam(value = "taux") Long taux, Model model){
        /**
         * recuperer le taux apres
         */
        Boutique boutique = new Boutique();
        boutique.setNom(pointDeRetrait);
        Transfert transfert = new Transfert();
        transfert.setMontantEuros(montant);
        transfert.setBoutique(boutique);
        transfert.setTaux((long) taux);
        /**
         * Recuperer l'utilisateur connecter
         * id
         * email
         * droit{client}
         * list beneficiaire
         */
        Client client = new Client();
        client.setBeneficiaires(clientService.listerBeneficiares());
        transfert.setClient(client);
        model.addAttribute("transfert", transfert);
        model.addAttribute("beneficiaire", new Beneficiaire());
        return "client/transfert";
    }

    @RequestMapping(value = "/beneficiaires", method = RequestMethod.POST)
    public String ajouterBeneficiaire(@Valid @ModelAttribute("beneficiaire") Beneficiaire beneficiaire,
                                      BindingResult bindingResult, Model model){
        /**
         * Gargder le montant et le taux dans le session
         */
        if(bindingResult.hasErrors()){
            return "client/transfert";
        }
        Transfert transfert = new Transfert();
        transfert.setMontantEuros((long) 500);
        transfert.setTaux((long) 10600);
        List<Beneficiaire> beneficiaires = clientService.listerBeneficiares();
        beneficiaires.add(beneficiaire);
        Client client = new Client();
        client.setBeneficiaires(beneficiaires);
        transfert.setClient(client);
        model.addAttribute("transfert", transfert);
        model.addAttribute("beneficiaire", new Beneficiaire());
        return "client/transfert";
    }

}
