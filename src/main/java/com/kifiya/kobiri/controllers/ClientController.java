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
import java.util.Map;

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

    @RequestMapping(value = {"/recapitulatif"}, method = RequestMethod.POST)
    public String initierTransfert(@RequestParam(value = "montantEuros") Long montant,
                                   @RequestParam(value = "pointDeRetrait") String pointDeRetrait,
                                   Model model){
        Map<String, Object> parametre = clientService.obtenirParametre();
        //model.addAttribute("boutiques", clientService.listerBoutiques());
        /**
         * recuperer le taux apres
         */
        Boutique boutique = new Boutique();
        boutique.setNom(pointDeRetrait);
        Transfert transfert = new Transfert();
        transfert.setMontantEuros(montant);
        transfert.setBoutique(boutique);
        transfert.setTaux(Double.parseDouble(parametre.get("taux").toString()));
        transfert.setFrais(Double.parseDouble(parametre.get("frais").toString())*montant);
        /**
         * Recuperer l'utilisateur connecter
         * id
         * email
         * droit{client}
         * list beneficiaire
         */
        //Client client = new Client();
        //client.setBeneficiaires(clientService.listerBeneficiares());
        //transfert.setClient(client);
        model.addAttribute("transfert", transfert);
        model.addAttribute("boutiques", parametre.get("boutiques"));
        return "client/transfert";
    }

    @RequestMapping(value = "/beneficiaires", method = RequestMethod.POST)
    public String ajouterBeneficiaire(@Valid @ModelAttribute("beneficiaire") Beneficiaire beneficiaire,
                                      BindingResult bindingResult, Model model){
        Transfert transfert = new Transfert();
        //Recuperer le client connecte dans la session
        Client client = new Client();
        //transfert.setMontantEuros((long) 500);
        //transfert.setTaux((long) 10600);
        List<Beneficiaire> beneficiaires = clientService.listerBeneficiares();
        /**
         * Garder le montant et le taux dans la session
         */
        if(bindingResult.hasErrors()){
            client.setBeneficiaires(beneficiaires);
            transfert.setClient(client);
            model.addAttribute("transfert", transfert);
            model.addAttribute("beneficiaire", new Beneficiaire());
            return "client/transfert";
        }
        if(beneficiaires.contains(beneficiaire)) {
            bindingResult.rejectValue("telephone", "error.user", "il ya béneficiaire enregistré avec ce numéro de telephone");
        } else {
            beneficiaires.add(beneficiaire);
        }
        client.setBeneficiaires(beneficiaires);
        transfert.setClient(client);
        model.addAttribute("transfert", transfert);
        model.addAttribute("beneficiaire", new Beneficiaire());
        model.addAttribute("step2", false);
        model.addAttribute("step3", false);
        return "client/transfert";
    }

    @RequestMapping(value = "/recapitulatif1", method = RequestMethod.POST)
    public String recapitulatif(@Valid @ModelAttribute("beneficiaire") Beneficiaire beneficiaire,
                                      BindingResult bindingResult, Model model){
        /**
         * Recuperer les inforamations dans la session
         */
        Transfert transfert = new Transfert();
        transfert.setMontantEuros((long) 500);
        //transfert.setTaux((long) 10600);
        transfert.setBeneficiaire(beneficiaire);
        transfert.setBoutique(new Boutique("Petel", "Mamou", ""));
        model.addAttribute("transfert", transfert);
        model.addAttribute("step2", true);
        model.addAttribute("step3", false);
        return "client/transfert-step2";
    }

    @RequestMapping(value = "/paiement", method = RequestMethod.POST)
    public String paiement(@Valid @ModelAttribute("transfert") Transfert transfert,
                                BindingResult bindingResult, Model model){
        model.addAttribute("transfert", transfert);
        model.addAttribute("step2", true);
        model.addAttribute("step3", true);
        return "client/transfert-step3";
    }

    @RequestMapping(value = "/paiement-carte", method = RequestMethod.POST)
    public String paiementParCarte(@Valid @ModelAttribute("transfert") Transfert transfert,
                           BindingResult bindingResult, Model model){
        //reinitialiser la variable de la session
        //model.addAttribute("transfert", transfert);
        clientService.ajouterTransfert(transfert);
        return "redirect:index";
    }

}
