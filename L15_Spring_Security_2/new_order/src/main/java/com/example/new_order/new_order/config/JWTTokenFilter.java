package com.example.new_order.new_order.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.new_order.new_order.entity.JWTClient;
import com.example.new_order.new_order.entity.S2SClient;
import com.example.new_order.new_order.repository.JWTClientRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

@Component
public class JWTTokenFilter extends OncePerRequestFilter {


    @Autowired
    JWTClientRepository repository;

    /**
     * S2S validation
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwtToken = request.getHeader("Authorization");
        jwtToken = jwtToken.replace("Bearer ", "");

        DecodedJWT decode = JWT.decode(jwtToken);
        String issuer = decode.getIssuer();
        Optional<JWTClient> jwtClient = repository.findByS2SClient(S2SClient.valueOf(issuer));
        JWTClient client = new JWTClient();
        client.setClientRoles(Collections.singleton("ANONYMOUS"));
        if(jwtClient.isPresent()){
            client = jwtClient.get();
        }
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(client, null, client.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        filterChain.doFilter(request, response);
    }



}
