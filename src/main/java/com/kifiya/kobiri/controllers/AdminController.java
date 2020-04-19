package com.kifiya.kobiri.controllers;

import com.kifiya.kobiri.services.BoutiqueService;
import com.kifiya.kobiri.services.GerantService;
import com.kifiya.kobiri.services.TransfertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    @Autowired
    private TransfertService transfertService;

    @Autowired
    private BoutiqueService boutiqueService;

    @Autowired
    private GerantService gerantService;

    @GetMapping("/admins")
    public String index(){
        return "admin/admin";
    }

    @GetMapping("/admins/accueil")
    public String getAdminStatistiques(Model model) {
        int nbTransfertsEncours = transfertService.determinerNombreDeTransfertsEnCours();
        int nbTransfertsRendus = transfertService.determinerNombreDeTransfertsRendus();
        int nbBoutiques = boutiqueService.nombreDeBoutiques();
        int nbGerants = gerantService.nombreDeGerants();

        model.addAttribute("nbTransfertsEncours", nbTransfertsEncours);
        model.addAttribute("nbTransfertsRendus", nbTransfertsRendus);
        model.addAttribute("nbGerants", nbGerants);
        model.addAttribute("nbBoutiques", nbBoutiques);

        return "admin/accueil-admin";
    }
}
