package com.kifiya.kobiri.config;

import org.springframework.stereotype.Component;

@Component
public class CustomUserAuthenticationProvider /** implements AuthenticationProvider */{
    /**
    @Autowired
    UtilisateurService utilisateurService;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();
        Utilisateur utilisateur = utilisateurService.connection(email, password);
        if(utilisateur == null){
            throw new BadCredentialsException("hop laa");
        }
        return new UsernamePasswordAuthenticationToken(utilisateur,null, Collections.emptyList());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
    */
}
