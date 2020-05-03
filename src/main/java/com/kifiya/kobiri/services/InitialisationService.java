package com.kifiya.kobiri.services;


import com.kifiya.kobiri.models.Transfert;
import com.kifiya.kobiri.models.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class InitialisationService {

    @Autowired
    UtilisateurService utilisateurService;
    @Autowired
    TransfertService transfertService;
    Utilisateur utilisateur = new Utilisateur();
    Utilisateur utilisateur1 = new Utilisateur();
    Utilisateur utilisateur2 = new Utilisateur();
    public void init() {
        initUsers();
        //initTransfert();
    }

    private void initUsers() {

        utilisateur.setConfirmationToken("");
        utilisateur.setNom("Golle");
        utilisateur.setPrenom("Sobhe");
        utilisateur.setEmail("sobhe@gmail.com");
        utilisateur.setTelephone("0606060606");
        utilisateur.setPays("No houdha");
        utilisateur.setVille("No feti");
        utilisateur.setCodePostale("06560");
        utilisateur.setAdresse("Geoges pompidou");
        utilisateur.setPassword("sobhe");
        utilisateur.setEnabled(true);
        utilisateur= utilisateurService.save(utilisateur);

        utilisateur1.setConfirmationToken("");
        utilisateur1.setNom("Golle");
        utilisateur1.setPrenom("Sobhe");
        utilisateur1.setEmail("sobhe1@gmail.com");
        utilisateur1.setTelephone("0606060606");
        utilisateur1.setPays("No houdha");
        utilisateur1.setVille("No feti");
        utilisateur1.setCodePostale("06560");
        utilisateur1.setAdresse("Geoges pompidou");
        utilisateur1.setPassword("sobhe");
        utilisateur1.setEnabled(true);
        utilisateur1 = utilisateurService.save(utilisateur1);

        utilisateur2.setConfirmationToken("");
        utilisateur2.setNom("Golle");
        utilisateur2.setPrenom("Sobhe");
        utilisateur2.setEmail("sobhe2@gmail.com");
        utilisateur2.setTelephone("0606060606");
        utilisateur2.setPays("No houdha");
        utilisateur2.setVille("No feti");
        utilisateur2.setCodePostale("06560");
        utilisateur2.setAdresse("Geoges pompidou");
        utilisateur2.setPassword("sobhe");
        utilisateur2.setEnabled(true);
        utilisateur2 = utilisateurService.save(utilisateur2);
    }


    private void initTransfert() {
        Transfert transfert1 = new Transfert();
        transfert1.setResponsable(utilisateur);
        transfert1.setNom("sobhe");
        transfert1.setPrenom("fiyaHollo");
        transfert1.setDateValidation(null);
        transfert1.setMontantEuros((long)500);
        transfert1.setTelephone("00224622191901");
        transfertService.init(transfert1);
        Transfert transfert2 = new Transfert();
        transfert1.setResponsable(utilisateur1);
        transfert2.setNom("sobhe");
        transfert2.setPrenom("fiyaHollo");
        transfert2.setDateValidation(null);
        transfert2.setMontantEuros((long)400);
        transfert2.setTelephone("00224644000001");
        transfertService.init(transfert2);
        Transfert transfert3 = new Transfert();
        transfert1.setResponsable(utilisateur2);
        transfert3.setNom("sobhe");
        transfert3.setPrenom("fiyaHollo");
        transfert3.setDateValidation(null);
        transfert3.setMontantEuros((long)300);
        transfert3.setTelephone("00224644000001");
        transfertService.init(transfert3);

    }


}
