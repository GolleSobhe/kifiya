package com.kifiya.kobiri.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SendingController {

    @PostMapping("envoi")
    public String initierEnvoi(){
        return "user/envoiForm";
    }
}
