package com.example.security_v2.security.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

import static com.example.security_v2.security.configuration.old.InMemorySecurityFilterOld.CUSTOMER;
import static com.example.security_v2.security.configuration.old.InMemorySecurityFilterOld.MERCHANT;

/**
 * Enable this if you want to use in memory authentication
 */
//@Configuration
public class InMemorySecurityFilter{

    /**
     * UserDetails -- by default a spring security context user / principal implements
     */

    /**
     * below is present for both 2.7.x and 3.0.0
     */
    @Bean
    InMemoryUserDetailsManager userDetailsManager(){
        UserDetails monika = User.withUsername("monika")
                .authorities(CUSTOMER)
                /**
                 *  for 3.0.0  and ** above if spring supports
                 */
                .password("$2a$10$XixtAGOEoF71ZBRknyrGQuIgT8PU3F4ADXryinmmGI2RVzfDMxemW")
                /**
                 * for 2.7.x it supports
                 */
//                .password("{bcrypt}$2a$10$XixtAGOEoF71ZBRknyrGQuIgT8PU3F4ADXryinmmGI2RVzfDMxemW")
                .build();


        UserDetails gunter = User.withUsername("gunter")
                .authorities(MERCHANT)
                .password("$2a$10$eC3EMqLKdrrd4mEtfcboRO.hKb4ka75GaF2zsJ2RehNhiO/ke.gIu")
                .build();

        UserDetails rachel = User.withUsername("rachel")
                .authorities(MERCHANT, CUSTOMER)
                .password("$2a$10$ugKNsuaAEawR10J1WKMkGefmufZXbcB83FeAvOP1dCd4CwLDvLWU6")
                .build();
        List<UserDetails> users = new ArrayList<>();
        users.add(monika);
        users.add(gunter);
        users.add(rachel);
        return new InMemoryUserDetailsManager(users);
    }

    @Bean
    @Primary
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    /**
     * Authorization
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .authorizeHttpRequests()
                .anyRequest()
                .permitAll()
                .and().formLogin();
        return http.build();
    }


}
