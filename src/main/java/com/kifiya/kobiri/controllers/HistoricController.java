package com.kifiya.kobiri.controllers;

import com.kifiya.kobiri.models.user.Historic;
import com.kifiya.kobiri.models.user.User;
import com.kifiya.kobiri.services.HistoricService;
import com.kifiya.kobiri.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import javax.validation.Valid;
import java.util.List;

@Controller
public class HistoricController {
    @Autowired
    private HistoricService historicService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "historics", method = RequestMethod.GET)
    public String getHistorics(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        model.addAttribute("historics", historicService.findByUserId(user.getId()));
        return "historic/historic";
    }

    @RequestMapping(value = "historics", method = RequestMethod.POST)
    public String postHistoric(@Valid @ModelAttribute("Historic") Historic historic,
                               BindingResult result, Model model){

        if (result.hasErrors()) {
            return "envoi/envoi";
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User userAuth = (User) auth.getPrincipal();
        User user = userService.findById(userAuth.getId());
        if(user == null) {
            return "envoi/envoi";
        }
        historic.setResponsable(user);
        List<Historic> historics = user.getHistorics();
        historics.add(historic);
        user.setHistorics(historics);
        userService.persist(user);
        model.addAttribute("historic", historic);
        model.addAttribute("confirmationMessage", "Argent enoyé et un e-mail de confirmation a été envoyé à ");
        return "envoi/envoi";
    }
}

