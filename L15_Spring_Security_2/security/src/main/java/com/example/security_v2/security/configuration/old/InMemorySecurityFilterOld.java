package com.example.security_v2.security.configuration.old;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.servlet.configuration.WebMvcSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class InMemorySecurityFilterOld extends WebMvcSecurityConfiguration {

        public static final String MERCHANT=  "ROLE_MERCHANT";
    public static final String CUSTOMER = "ROLE_CUSTOMER";


    /**
     * Authentication And Authorization
     */

        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("monika")
                .authorities(CUSTOMER)
                .password("$2a$10$XixtAGOEoF71ZBRknyrGQuIgT8PU3F4ADXryinmmGI2RVzfDMxemW")
                .and()
                .withUser("gunter")
                .authorities(MERCHANT)
                .password("$2a$10$eC3EMqLKdrrd4mEtfcboRO.hKb4ka75GaF2zsJ2RehNhiO/ke.gIu")
                .and()
                .withUser("rachel")
                .authorities(MERCHANT, CUSTOMER)
                .password("$2a$10$ugKNsuaAEawR10J1WKMkGefmufZXbcB83FeAvOP1dCd4CwLDvLWU6");
    }


    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("monika"));
        System.out.println(encoder.encode("gunter"));
        System.out.println(encoder.encode("rachel"));

    }




    @Bean
    @Primary
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

//    this.logger.debug("Using default configure(HttpSecurity). If subclassed this will potentially override subclass configure(HttpSecurity).");
//        http.authorizeRequests((requests) -> {
//        ((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)requests.anyRequest()).authenticated();
//    });
//        http.formLogin();
//        http.httpBasic();

    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .anyRequest()
                .permitAll()
                .and().formLogin();
    }




}
