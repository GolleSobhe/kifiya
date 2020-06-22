package com.kifiya.kobiri.controllers;

import com.kifiya.kobiri.models.Boutique;
import com.kifiya.kobiri.services.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
    public String index(){
        return "admin/admin";
    }

    @RequestMapping(value = "/accueil", method = RequestMethod.GET)
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

    @RequestMapping(value = "/boutiques", method = RequestMethod.POST)
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

    @RequestMapping(value = "/boutiques", method = RequestMethod.GET)
    public String listeBoutiques(Model model){
        model.addAttribute("boutiques", adminService.listerBoutiques());
        model.addAttribute("boutique",new Boutique());
        return "boutique/boutique";
    }
}
