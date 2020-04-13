package com.kifiya.kobiri.services;


import com.kifiya.kobiri.models.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class InitialisationService {

    @Autowired
    UtilisateurService utilisateurService;

    public void init() {
        initUsers();
    }

    private void initUsers() {
        Utilisateur utilisateur = new Utilisateur();
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
        utilisateurService.save(utilisateur);
        Utilisateur utilisateur1 = new Utilisateur();
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
        utilisateurService.save(utilisateur1);
        Utilisateur utilisateur2 = new Utilisateur();
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
        utilisateurService.save(utilisateur2);
    }







}
