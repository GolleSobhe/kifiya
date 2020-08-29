package com.kifiya.kobiri.config;

import com.kifiya.kobiri.models.Utilisateur;
import com.kifiya.kobiri.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CustomUserAuthenticationProvider  implements AuthenticationProvider {

    @Autowired
    UtilisateurService utilisateurService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();
        Utilisateur utilisateur = utilisateurService.connexion(email, password);
        if(utilisateur == null){
            throw new BadCredentialsException("hop laa");
        }
        if(bCryptPasswordEncoder.matches(password, utilisateur.getPassword())){
            var autorithies = new HashSet<GrantedAuthority>();
            autorithies.add(new GrantedAuthority() {
                @Override
                public String getAuthority() {
                    return "ROLE_"+ utilisateur.getRole();
                }
            });
            return new UsernamePasswordAuthenticationToken(utilisateur.getEmail(),utilisateur.getPassword(), autorithies);
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }

}
