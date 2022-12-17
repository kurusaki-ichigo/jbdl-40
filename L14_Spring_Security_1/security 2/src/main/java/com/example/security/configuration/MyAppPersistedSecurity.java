package com.example.security.configuration;


import com.example.security.service.UserService;
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

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class MyAppPersistedSecurity extends WebSecurityConfigurerAdapter {


    public static final String MERCHANT =  "ROLE_MERCHANT";
    public static final String CUSTOMER = "ROLE_CUSTOMER";

    public static final String USER_SERVICE = "userService";

    @Autowired
    BeanFactory  beanFactory;

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        UserService userService = (UserService) beanFactory.getBean(USER_SERVICE);
     auth.userDetailsService(userService);
    }


    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("monika"));
        System.out.println(encoder.encode("gunter"));
        System.out.println(encoder.encode("rachel"));

    }


    /**
     *
     *  -- Payytm.com
     *      (FE is same)        --> Username (abc@hello.com)        --> Make call to paytm
     *                          --> password (abc)                  --> Make call to own DB --> to save user credentials
     *
     *                      CORS resource shaqring
     *                      csrf cross site request forgery .enable
     *
     *                      -- ORIGIN ---> www.Payytm.com
     *                      --- HOST ---> www.paytm.com
     *
     *      Paytm
     *  (FE is same)
     * @return
     */



    @Bean
    @Primary
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }



    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .anyRequest()
                .permitAll()
                .and().formLogin();
    }


}
