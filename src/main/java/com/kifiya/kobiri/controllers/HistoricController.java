package com.kifiya.kobiri.controllers;

import com.kifiya.kobiri.models.user.Historic;
import com.kifiya.kobiri.services.HistoricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import javax.validation.Valid;

@Controller
public class HistoricController {
    @Autowired
    private HistoricService historicService;

    @RequestMapping(value = "historics", method = RequestMethod.GET)
    public String getHistorics(Model model){
        model.addAttribute("historics", historicService.findAll());
        return "historic/historic";
    }

    @RequestMapping(value = "historics", method = RequestMethod.POST)
    public String postHistoric(@Valid @ModelAttribute("Historic") Historic historic,
                               BindingResult result, Model model){

        if (result.hasErrors()) {
            return "envoi/envoi";
        }

        historicService.save(historic);
        model.addAttribute("historic", historic);
        return "index";
    }
}

