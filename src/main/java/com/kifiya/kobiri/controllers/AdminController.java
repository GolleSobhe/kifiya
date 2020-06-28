package com.kifiya.kobiri.controllers;

import com.kifiya.kobiri.models.Boutique;
import com.kifiya.kobiri.models.Gerant;
import com.kifiya.kobiri.services.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping(value = "/")
    public String index(){
        return "admin/admin";
    }

    @GetMapping(value = "/accueil")
    public String getAdminStatistiques(Model model) {
        int nbTransfertsEncours = adminService.determinerNombreDeTransfertsEnCours();
        int nbTransfertsRendus = adminService.determinerNombreDeTransfertsRendus();
        int nbBoutiques = adminService.nombreDeBoutiques();
        int nbGerants = adminService.nombreDeGerants();

        model.addAttribute("nbTransfertsEncours", nbTransfertsEncours);
        model.addAttribute("nbTransfertsRendus", nbTransfertsRendus);
        model.addAttribute("nbGerants", nbGerants);
        model.addAttribute("nbBoutiques", nbBoutiques);

        return "admin/accueil-admin";
    }

    @GetMapping(value = "/gerant")
    public String ajouterGerant(Model model){
        model.addAttribute("gerant",new Gerant());
        return "gerant/gerantForm";
    }

    @PostMapping(value = "/gerant")
    public String sauverGerant(@Valid Gerant gerant, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "gerant/gerantForm";
        }
        adminService.ajouterGerant(gerant);
        return "index";
    }


    @PostMapping(value = "/boutiques")
    public String ajouterBoutique(@Valid @ModelAttribute("boutique") Boutique boutique,
                          BindingResult result, Model model){
        if (result.hasErrors()) {
            //AJouter le modele error de saissie
            return "error/400";
        }
        adminService.ajouterBoutique(boutique);
        model.addAttribute("boutiques", adminService.listerBoutiques());
        model.addAttribute("boutique",new Boutique());
        return "boutique/boutique";
    }

    @GetMapping(value = "/boutiques")
    public String listeBoutiques(Model model){
        model.addAttribute("boutiques", adminService.listerBoutiques());
        model.addAttribute("boutique",new Boutique());
        return "boutique/boutique";
    }
}
