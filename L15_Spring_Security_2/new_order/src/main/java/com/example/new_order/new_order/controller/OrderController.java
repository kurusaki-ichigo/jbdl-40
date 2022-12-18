package com.example.new_order.new_order.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping(value = "/order", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> welcomeDeliveryPartner(){
        return new ResponseEntity<>("Hey I am accessible  by everyone ! ", HttpStatus.OK);
    }


    /**
     *
     * JWT
     *
     * eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJodHRwczpcL1wvd3d3LmdlZWtzZm9yZ2Vla3Mub3JnXC8iLCJpYXQiOjE2NzEzODA2NDAsImV4cCI6MTY3Mzk3MjY0MCwiaGFuZGxlIjoicHJhbmF2X21pZ2xhbmkiLCJ1dWlkIjoiNDM1YmNiYTBhMWIzNWM4YTM2MjFhNTZmMzQ5NTk2ZGIiLCJwcm9maWxlVXJsIjoiaHR0cHM6XC9cL21lZGlhLmdlZWtzZm9yZ2Vla3Mub3JnXC9pbWctcHJhY3RpY2VcL3VzZXJfd2ViLTE1OTg0MzMyMjguc3ZnIiwiaW5zdGl0dXRlSWQiOjAsImluc3RpdHV0ZU5hbWUiOiJwYXl0bSIsIm5hbWUiOiIiLCJpc0ludGVyZXN0U2VsZWN0ZWQiOmZhbHNlLCJwdWlkIjoidUcrSlN0c1wvIiwicGEiOjF9.JbTLWNWfPXArUbTIP6JcR1weY8wiLywKzgKpW3qhTMwARwZ0FjXpKxGz6hzsYgw7UaE0O2d3FuFrjPON8S62Xe-5mX_WaBq5NkaTozJJRdgyL3qZYoNha02KPfEtwoX6oCxKT6Cs5Xl2WiMMTRQtPrmSx0JjyCu0F9wlk0QEDJuf044yhuqteH2-ZXYEQHqHcLqgZUFfO2s2j-ogjr8ChE0hwihDRrP7MVba921QktNKxNYo_859JgUaplhmXwbpEzR_Kq7RfjDwSr3coLsglP1jPQ4XC2eqELT2JbS-vU6Uk91aYi13i1vvtt1YlVdS59G2hRZPBBy2KnH0UpHGXg
     *
     *
     * FE ---> interacts with BE
     *  (sending session token)
     *
     *  BE ---> BE
     *              --- call via event bus ---> whitelisting the consumer group
     *                                          (this is done at kafka)
     *
     *             --- making rest api calls ---->
     *                      you would need to authenticate the another service which is making the call
     *
     *
     *                          S2S authetication and authorization -- JWT Token
     *                      (/order ) -----> (/payment)
     *                                       /
     *                      (hacker)--------/
     *
     *
     *                      It needs to authenticate what is source of the request
     *
     *                      (JWT TOKEN)
     *                      JSON WEB TOKEN
     *
     *
     *
     *
     *
     *          (one service) -------> sending message -------> another service
     *
     *
     *
     *
     *
     *
     *
     */

}
