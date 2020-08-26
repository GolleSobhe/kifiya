package com.kifiya.kobiri.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("models")
@Tag("junit5")
@SpringBootTest
@ExtendWith(SpringExtension.class)
class UtilisateurTest {

    private static Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
/**
    @Test
    void testNomVide() {
        Utilisateur utilisateur = Utilisateur.builder().nom("").prenom("Hollo").email("sobe@gmail.com").password("Sobhe")
                .active(true).confirmationToken("").build();
        assertTrue(validator.validate(utilisateur).size() != 0);
    }

    @Test
    void testPrenomVide() {
        Utilisateur utilisateur = Utilisateur.builder().nom("Hello").prenom("").email("sobe@gmail.com").password("Sobhe")
                .active(true).confirmationToken("").build();
        assertTrue(validator.validate(utilisateur).size() != 0);
    }



    @Test
    void testEmailVide() {
        Utilisateur utilisateur = Utilisateur.builder().nom("Hello").prenom("Abd").email("").password("Sobhe")
                .active(true).confirmationToken("").build();
        assertTrue(validator.validate(utilisateur).size() != 0);
    }

    @Test
    void testEmailNonValide() {
        Utilisateur utilisateur = Utilisateur.builder().nom("Hello").prenom("Abd").email("pititi").password("Sobhe")
                .active(true).confirmationToken("").build();
        assertTrue(validator.validate(utilisateur).size() != 0);
    }

    @Test
    void testUtilisateurValide() {
        Utilisateur utilisateur = Utilisateur.builder().nom("No Feti").prenom("Hollo").email("sobe@gmail.com").build();
        assertTrue(validator.validate(utilisateur).size() == 0);
    }

    @Test
    void testUtilisateurValideTousLesAttributs() {
        Utilisateur utilisateur = Utilisateur.builder().nom("No Feti").prenom("Hollo").email("sobe@gmail.com").password("Sobhe")
                .active(true).confirmationToken("").build();
        assertTrue(validator.validate(utilisateur).size() == 0);
    }
*/
}