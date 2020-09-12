package com.kifiya.kobiri.config;

import org.springframework.stereotype.Component;

@Component
public class CustomUserAuthenticationProvider /** implements AuthenticationProvider **/{
    /**
     *

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
     */
}
