package com.kifiya.kobiri.controllers;

import com.kifiya.kobiri.services.BoutiqueService;
import com.kifiya.kobiri.services.GerantService;
import com.kifiya.kobiri.services.TransfertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private TransfertService transfertService;

    @Autowired
    private BoutiqueService boutiqueService;

    @Autowired
    private GerantService gerantService;

    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
    public String index(){
        return "admin/admin";
    }

    @RequestMapping(value = "/accueil", method = RequestMethod.GET)
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
