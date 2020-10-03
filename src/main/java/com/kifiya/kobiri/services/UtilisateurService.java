package com.kifiya.kobiri.services;

import com.kifiya.kobiri.models.Utilisateur;
import com.kifiya.kobiri.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public Utilisateur connexion(String email) {
        return  utilisateurRepository.connexion(email);
    }
}
