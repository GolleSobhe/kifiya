package com.kifiya.kobiri.services;


import com.kifiya.kobiri.models.Client;
import com.kifiya.kobiri.models.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class InitialisationService {

    @Autowired
    UtilisateurService utilisateurService;
    Utilisateur client = new Client();
    Utilisateur client1 = new Client();
    Utilisateur client2 = new Client();
    public void init() {
        initUsers();
    }

    private void initUsers() {

        client.setConfirmationToken("");
        client.setNom("Golle");
        client.setPrenom("Sobhe");
        client.setEmail("sobhe@gmail.com");
        client.setTelephone("0606060606");
        client.setPays("No houdha");
        client.setVille("No feti");
        client.setCodePostale("06560");
        client.setAdresse("Geoges pompidou");
        client.setPassword("sobhe");
        client.setEnabled(true);
        client= utilisateurService.ajouter(client);

        client1.setConfirmationToken("");
        client1.setNom("Golle");
        client1.setPrenom("Sobhe");
        client1.setEmail("sobhe1@gmail.com");
        client1.setTelephone("0606060606");
        client1.setPays("No houdha");
        client1.setVille("No feti");
        client1.setCodePostale("06560");
        client1.setAdresse("Geoges pompidou");
        client1.setPassword("sobhe");
        client1.setEnabled(true);
        client1 = utilisateurService.ajouter(client1);

        client2.setConfirmationToken("");
        client2.setNom("Golle");
        client2.setPrenom("Sobhe");
        client2.setEmail("sobhe2@gmail.com");
        client2.setTelephone("0606060606");
        client2.setPays("No houdha");
        client2.setVille("No feti");
        client2.setCodePostale("06560");
        client2.setAdresse("Geoges pompidou");
        client2.setPassword("sobhe");
        client2.setEnabled(true);
        client2 = utilisateurService.ajouter(client2);
    }
}
