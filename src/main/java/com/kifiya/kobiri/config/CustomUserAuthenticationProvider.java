package com.kifiya.kobiri.config;

import com.kifiya.kobiri.models.Utilisateur;
import com.kifiya.kobiri.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

@Component
public class CustomUserAuthenticationProvider  implements AuthenticationProvider {

    @Autowired
    UtilisateurService utilisateurService;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();
        Utilisateur utilisateur = utilisateurService.connexion(email, password);
        if(utilisateur == null){
            throw new BadCredentialsException("hop laa");
        }
        final Authentication result = super.authenticate(auth);
        return new UsernamePasswordAuthenticationToken(utilisateur,utilisateur.getPassword(), utilisateur.getRole());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }

}
