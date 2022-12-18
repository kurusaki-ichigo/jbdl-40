//package com.example.new_order.new_order.config;
//
//import com.example.new_order.new_order.response.UserInfo;
//import com.google.gson.Gson;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@Component
//@Slf4j
//public class ThirdPartyFilter  extends OncePerRequestFilter {
//
//
//    @Autowired
//    RestTemplate restTemplate;
//
//    @Autowired
//    Gson gson;
//
//
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        /**
//         * Make a call to user microservice
//         * fetch the data and append user roles
//         */
//        String emailId = request.getParameter("emailId");
//        String userResponse = restTemplate.getForObject("http://localhost:8080/user/" + emailId, String.class);
//        log.info("responseReceived {} ", userResponse);
//        UserInfo userInfo = gson.fromJson(userResponse, UserInfo.class);
//        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
//                new UsernamePasswordAuthenticationToken(userInfo,null, userInfo.getAuthorities());
//        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//        filterChain.doFilter(request, response);
//    }
//}
