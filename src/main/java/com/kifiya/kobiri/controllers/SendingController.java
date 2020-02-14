package com.kifiya.kobiri.controllers;

import com.kifiya.kobiri.models.user.Historic;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SendingController {

    @PostMapping("avoirapres")
    public String initierEnvoi(){
        return "user/envoiForm";
    }

    @RequestMapping(value = "envoi", method = RequestMethod.GET)
    public String envoi(Model model){
        model.addAttribute("historic", new Historic());
        return "envoi/envoi";
    }
}
