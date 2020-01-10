package com.kifiya.kobiri.config;

//@Configuration
//@EnableWebSecurity
public class WebSecurityConfig /*extends WebSecurityConfigurerAdapter*/ {


    /*private CustomUserAuthenticationProvider customUserAuthenticationProvider;

    @Autowired
    public WebSecurityConfig(CustomUserAuthenticationProvider customUserAuthenticationProvider){
        this.customUserAuthenticationProvider = customUserAuthenticationProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/","/index","/user/new","/user").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/user/signIn").permitAll()
                .and()
                .logout().permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customUserAuthenticationProvider);
    }*/

}
