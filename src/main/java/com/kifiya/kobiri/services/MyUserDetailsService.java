package com.kifiya.kobiri.services;

import com.kifiya.kobiri.models.MyUserDetails;
import com.kifiya.kobiri.models.Utilisateur;
import com.kifiya.kobiri.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Override
    public UserDetails loadUserByUsername(String email){

        Optional<Utilisateur> utilisateur = utilisateurRepository.userByEmail(email);
        utilisateur.orElseThrow(() -> new UsernameNotFoundException("Utilisateur n'existe pas"));
        return utilisateur.map(MyUserDetails::new).get();
    }
}
