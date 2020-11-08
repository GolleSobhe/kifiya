package com.kifiya.kobiri.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DaoAuthenticationProvider authenticationProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                .antMatchers("/", "/index", "acceuil", "/utilisateur/inscription", "/utilisateur/contact", "/utilisateur/**", "/client/recapitulatif").permitAll()
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .antMatchers("/client/**").hasAuthority("CLIENT")
                .antMatchers("/gerant/**").hasAuthority("GERANT")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/utilisateur/connexion")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/utilisateur/deconnexion")
                .permitAll()
                .and().csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**",  "/console/**", "/h2-console/**");
    }

}
