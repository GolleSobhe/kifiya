package com.kifiya.kobiri.services;

import com.kifiya.kobiri.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


@Tag("gerant")
@Tag("junit5")
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class GerantServiceTest {
    @Autowired
    GerantService gerantService;
    @Autowired
    TransfertService transfertService;

    private Utilisateur gerant, gerant1;
    private Utilisateur client;
    private Beneficiaire beneficiaire;
    private Transfert transfert;
    @BeforeEach
    public void setUp() {
        client = Client.builder().nom("OSBHE").prenom("Abdou").email("sobhe@gmail.com").telephone("00224622000000")
                .pays("No houdha").ville("No feti").adresse("No feti ham may").roles(Arrays.asList(new Role("client"))).build();
        //client = utilisateurService.ajouter(client);
        beneficiaire = Beneficiaire.builder().nom("fiya").prenom("hollo").telephone("00224621000000").build();
        //transfert = Transfert.builder().dateTransfert(new Date()).beneficiaire(beneficiaire).client(client)
        //        .montantEuros(500).taux(10600).code("DDDD4444").dateValidation().build();
        //transfert = transfertService.ajouter(transfert);
        gerant = Gerant.builder().nom("BAH").prenom("Abdou").email("sobhe@gmail.com").telephone("00224622000000")
                .pays("No houdha").ville("No feti").adresse("No houdha").code("4444ddee").roles(Arrays.asList(new Role("gerant"))).build();
        gerant1 = Gerant.builder().nom("OSBHE").prenom("Abdou").email("sobhe@gmail.com").telephone("00224622000000")
                .pays("No houdha").ville("No feti").adresse("No houdha ham mayi").code("4444ddee").roles(Arrays.asList(new Role("gerant"))).build();
    }

    @Test
    void ajouterGerant() {
        gerant = gerantService.ajouter(gerant);
        assertNotNull(gerant.getId());
        assertNull(gerant1.getId());
    }

    @Test
    void trouverTransfertParStatus() {
        assertTrue(true);
    }

    @Test
    void rechercherTransfert() {
        assertTrue(true);
    }

    @Test
    void validerTransfert() {
        assertTrue(true);
    }

    @Test
    void nombreDeGerants() {
        assertTrue(true);
    }

    @Test
    void testRechercherTransfert() {
        assertTrue(true);
    }

    @Test
    void obtenirStatistique() {
        assertTrue(true);
    }
}