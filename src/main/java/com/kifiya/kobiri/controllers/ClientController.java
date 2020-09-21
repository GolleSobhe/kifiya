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

    @RequestMapping(value = {"/recapitulatif"}, method = RequestMethod.POST)
    public String initierTransfert(@RequestParam(value = "montantEuros") Long montant,
                                   @RequestParam(value = "pointDeRetrait") String pointDeRetrait,
                                   Model model){
        Map<String, Object> parametre = clientService.obtenirParametre();
        Transfert transfert = Transfert.builder()
                .boutique(Boutique.builder().nom(pointDeRetrait).build())
                .taux(Double.parseDouble(parametre.get("taux").toString()))
                .frais(Double.parseDouble(parametre.get("frais").toString())*montant)
                .montantEuros(montant)
                .build();
        model.addAttribute("transfert", transfert);
        model.addAttribute("boutiques", parametre.get("boutiques"));
        return "client/transfert";
    }

    @RequestMapping(value = "/transfert", method = RequestMethod.POST)
    public String changerTransfert(@ModelAttribute("transfert") Transfert transfert, Model model){
        List<Beneficiaire> beneficiaires = clientService.listerBeneficiares();
        //transfert.setClient(Client.builder().beneficiaires(beneficiaires).build());
        Client client = new Client();
        client.setBeneficiaires(beneficiaires);
        transfert.setClient(client);
        model.addAttribute("transfert", transfert);
        model.addAttribute("beneficiaire", new Beneficiaire());
        model.addAttribute("step2", true);
        model.addAttribute("step3", false);
        return "client/transfert-beneficiaire";
    }

    @RequestMapping(value = "/beneficiaires", method = RequestMethod.POST)
    public String ajouterBeneficiaire(@Valid @ModelAttribute("beneficiaire") Beneficiaire beneficiaire, Model model){
        Transfert transfert = new Transfert();
        transfert.setBeneficiaire(beneficiaire);
        model.addAttribute("transfert", transfert);
        model.addAttribute("beneficiaire", new Beneficiaire());
        model.addAttribute("step2", true);
        model.addAttribute("step3", true);
        return "client/transfert-step3";
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
