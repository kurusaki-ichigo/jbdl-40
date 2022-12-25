package com.example.ewallet.notification.service;

import com.example.ewallet.notification.models.UserInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
@Slf4j
public class UserService {



    @Value("${client.user_info.fetch_user_url}")
    String fetchUserEndPoint;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ObjectMapper objectMapper;
    @SneakyThrows
    public UserInfo fetchUserInfo(UUID userId){
        String response = restTemplate.getForObject(fetchUserEndPoint + userId, String.class);
        log.info(" response received {} ", response);
        return objectMapper.readValue(response, UserInfo.class);
    }
}
