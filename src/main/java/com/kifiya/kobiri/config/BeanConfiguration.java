package com.kifiya.kobiri.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration /**  implements WebMvcConfigurer **/{
    /**
     *
     * @return

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Bean
    ServletRegistrationBean h2servletRegistration() {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new WebServlet());
        registrationBean.addUrlMappings("/console/*");
        return registrationBean;
    }
     */
}
