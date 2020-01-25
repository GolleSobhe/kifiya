package com.kifiya.kobiri.config;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.kifiya.kobiri.models.user.User;
import com.kifiya.kobiri.repositories.UserRepository;

@Component
public class CustomUserAuthenticationProvider implements AuthenticationProvider {

    private UserRepository userRepository;

    @Autowired
    public CustomUserAuthenticationProvider(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userMail = authentication.getName();
        String password = authentication.getCredentials().toString();
        User user = userRepository.connection(userMail,password);
        if(user == null){
            throw new BadCredentialsException("hop laa");
        }
        return new UsernamePasswordAuthenticationToken(user.getNom(),null, Collections.emptyList());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }

	
}
