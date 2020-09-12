package com.kifiya.kobiri.config;


import org.springframework.context.annotation.Configuration;


@Configuration
//@EnableWebSecurity
public class WebSecurityConfig /** extends WebSecurityConfigurerAdapter **/ {
    /**
     *

    @Autowired
    private CustomUserAuthenticationProvider customUserAuthenticationProvider;
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/index", "acceuil", "/utilisateur/inscription", "/utilisateur/confirmation").permitAll()
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .antMatchers("/client/**").hasAuthority("CLIENT")
                .antMatchers("/gerant/**").hasAuthority("GERANT")
                .anyRequest().authenticated()
                .and().csrf().disable()
                .formLogin()
                .loginPage("/utilisateur/connexion").permitAll()
                .and()
                .logout().permitAll();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**",  "/console/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customUserAuthenticationProvider);
    }
     */
}
