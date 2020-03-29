package com.kifiya.kobiri.controllers;

import com.kifiya.kobiri.models.user.Transfert;
import com.kifiya.kobiri.models.user.User;
import com.kifiya.kobiri.services.TransfertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Date;

@Controller
public class TransfertController {

    @PostMapping("avoirapres")
    public String initierEnvoi(){
        return "user/envoiForm";
    }

    @RequestMapping(value = "envoi", method = RequestMethod.GET)
    public String envoi(Model model){
        model.addAttribute("transfert", new Transfert());
        return "envoi/transfert";
    }

    @Autowired
    private TransfertService transfertService;

    @RequestMapping(value = "transferts", method = RequestMethod.GET)
    public String getHistorics(Model model){
        model.addAttribute("transferts", transfertService.findByUserId());
        return "historic/historic";
    }

    @RequestMapping(value = "transferts", method = RequestMethod.POST)
    public String postHistoric(@Valid @ModelAttribute("transfert") Transfert transfert,
                               BindingResult result, Model model){

        //if (result.hasErrors() || !result.hasFieldErrors("responsable")) {
        //    return "envoi/envoi";
        //}

        transfertService.save(transfert);

        model.addAttribute("transfert", transfert);
        model.addAttribute("confirmationMessage", "Argent enoyé et un e-mail de confirmation a été envoyé à ");
        return "envoi/transfert";
    }
}
