package com.kifiya.kobiri.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admins")
    public String index(){
        return "admin/admin";
    }

    @GetMapping("/admins/accueil")
    public String accueil() {
        return "admin/accueil-admin";
    }
}
