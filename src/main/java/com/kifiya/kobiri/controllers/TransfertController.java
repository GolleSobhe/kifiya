package com.kifiya.kobiri.controllers;

import com.kifiya.kobiri.models.Transfert;
import com.kifiya.kobiri.services.TransfertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/transferts")
public class TransfertController {

    @Autowired
    private TransfertService transfertService;


    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
    public String faireTransfert(Model model){
        model.addAttribute("transfert", new Transfert());
        return "transfert/transfert";
    }

    @RequestMapping(value =  {"/", ""}, method = RequestMethod.POST)
    public String postHistoric(@Valid @ModelAttribute("transfert") Transfert transfert,
                               BindingResult result, Model model){

        if (result.hasErrors() || !result.hasFieldErrors("client") || !result.hasFieldErrors("beneficiaire")) {
            //Ajouter le message d'erreur sur le model
            return"transfert/transfert";
        }

        transfertService.ajouter(transfert);

        model.addAttribute("transfert", transfert);
        model.addAttribute("confirmationMessage", "Argent enoyé et un e-mail de confirmation a été envoyé à ");
        return "transfert/transfert";
    }
}
