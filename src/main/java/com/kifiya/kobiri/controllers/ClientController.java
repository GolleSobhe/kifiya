package com.kifiya.kobiri.controllers;

import com.kifiya.kobiri.models.*;
import com.kifiya.kobiri.services.ClientService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

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
    public String changerTransfert(HttpSession httpSession, @ModelAttribute("transfert") Transfert transfert, Model model){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Beneficiaire> beneficiaires = clientService.listerBeneficiares(userDetails.getUsername());
        Client client = Client.builder()
                .beneficiaires(beneficiaires)
                .email(userDetails.getUsername())
                .active(userDetails.isEnabled())
                .nom("").prenom("").ville("").telephone("").adresse("").pays("")
                .build();
        transfert.setClient(client);
        model.addAttribute("transfert", transfert);
        model.addAttribute("beneficiaire", new Beneficiaire());
        model.addAttribute("step2", true);
        model.addAttribute("step3", false);
        httpSession.setAttribute("transfert", transfert);
        return "client/transfert-beneficiaire";
    }

    @RequestMapping(value = "/beneficiaires", method = RequestMethod.POST)
    public String ajouterBeneficiaire(HttpSession httpSession, @ModelAttribute("beneficiaire") Beneficiaire beneficiaire, Model model){
        Transfert transfert = (Transfert) httpSession.getAttribute("transfert");
        transfert.setBeneficiaire(beneficiaire);
        httpSession.setAttribute("transfert", transfert);
        model.addAttribute("carte", new Carte());
        model.addAttribute("step2", true);
        model.addAttribute("step3", true);
        return "client/transfert-carte";
    }

    @RequestMapping(value = "/paiement-carte", method = RequestMethod.POST)
    public String paiementParCarte(HttpSession httpSession, @Valid @ModelAttribute("carte") Carte carte, Model model){
        Transfert transfert = (Transfert) httpSession.getAttribute("transfert");
        assertNotNull(carte);
        Transfert transfertSaved = clientService.ajouterTransfert(transfert);
        model.addAttribute("transfert", transfertSaved);
        return "client/transfert-enregistre";
    }

}
