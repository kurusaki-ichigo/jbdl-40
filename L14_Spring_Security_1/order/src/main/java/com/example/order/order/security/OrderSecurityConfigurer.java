package com.example.order.order.security;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class OrderSecurityConfigurer extends WebSecurityConfigurerAdapter {


//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        UserService userService = (UserService) beanFactory.getBean(USER_SERVICE);
//        auth.userDetailsService(userService);
//        auth.authenticationEventPublisher()
//    }


    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("monika"));
        System.out.println(encoder.encode("gunter"));
        System.out.println(encoder.encode("rachel"));

    }



    @Autowired
    ThirdPartyUserFilter userFilter;

    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .anyRequest()
                .permitAll()
                .and()
                .addFilterBefore(userFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
