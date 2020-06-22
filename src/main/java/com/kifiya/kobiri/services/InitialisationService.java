package com.kifiya.kobiri.services;


import com.kifiya.kobiri.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Service
@Transactional
public class InitialisationService {

    @Autowired
    ClientService clientService;

    @Autowired
    private  GerantService gerantService;

    Client client;
    Client client1;
    Client client2;
    Admin admin;
    Gerant gerant;
    public void init() {
        initUsers();
    }

    private void initUsers() {

        client = Client.builder().nom("Golle").prenom("Sobhe").email("sobhe@gmail.com").password("sobhe").telephone("0606060606")
                .pays("No Houdha").ville("No Feti").adresse("Ham mayi").codePostale("06000").
                build();
        clientService.ajouter(client);

        client1 = Client.builder().nom("Golle").prenom("Sobhe").email("sobhe1@gmail.com").password("sobhe")
                    .pays("No Houdha").ville("No Feti").adresse("Ham mayi").build();
        clientService.ajouter(client1);

        client2 = Client.builder().nom("Golle").prenom("Sobhe").email("sobhe2@gmail.com").password("sobhe")
                .pays("No Houdha").ville("No Feti").adresse("Ham mayi").build();
        clientService.ajouter(client2);

        admin = Admin.builder().nom("BAH").prenom("Abdou").email("admin@gmail.com").password("admin").telephone("0606060606")
                .pays("No houdha").ville("No feti").adresse("Pompidou").active(true).confirmationToken("")
                .roles(Arrays.asList(new Role("admin"))).build();

        gerant = Gerant.builder().nom("Golle").prenom("Sobhe").password("sobhe").telephone("00224622000000")
                .ville("No Feti").adresse("Ham mayi").code("FFFF4444").build();
        gerantService.ajouter(gerant);
    }
}
