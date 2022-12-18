package com.example.security_v2.security.configuration;

import com.example.security_v2.security.service.UserService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class OwnUserFilter {


    public static final String USER_SERVICE = "USER_SERVICE";
    /**
     * User Details service
     * --> user details object
     * ---> our own userINfo maanged
     */

    @Autowired
    BeanFactory beanFactory;

    /**
     *
     * @return
     */
    @Bean
    DaoAuthenticationProvider daoAuthenticationProvider(){
        UserService userService = (UserService) beanFactory.getBean(USER_SERVICE);
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
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
