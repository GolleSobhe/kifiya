package com.kifiya.kobiri.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SendingController {

    @PostMapping("avoirapres")
    public String initierEnvoi(){
        return "user/envoiForm";
    }

    @RequestMapping("envoi")
    public String evnoi(Model model){
        return "envoi/envoi";
    }
}
