package com.kifiya.kobiri.config;

import com.kifiya.kobiri.models.user.User;
import com.kifiya.kobiri.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class CustomUserAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    UserService userService;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();
        User user = userService.connection(email, password);
        if(user == null){
            throw new BadCredentialsException("hop laa");
        }
        return new UsernamePasswordAuthenticationToken(user,null, Collections.emptyList());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
