package com.kifiya.kobiri.config;

import com.kifiya.kobiri.models.Utilisateur;
import com.kifiya.kobiri.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UtilisateurConnecterImpl implements UserDetailsService {

    @Autowired
    UtilisateurService utilisateurService;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Utilisateur utilisateur = utilisateurService.connexion(email);
        if (utilisateur == null){
            throw new UsernameNotFoundException("Utilisateur n'existe pas");
        }
        return new UtilisateurConnecter(utilisateur);
    }
}
