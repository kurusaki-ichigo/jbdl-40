package com.example.L8;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
//import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class ApplicationStartupListener implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    RestTemplate restTemplate;

    @SneakyThrows
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

//        OkHttpClient client = new OkHttpClient().newBuilder()
//                .build();
//        MediaType mediaType = MediaType.parse("text/plain");
//        RequestBody body = RequestBody.create(mediaType, "");
//        Request request = new Request.Builder()
//                .url("http://localhost:8080/book?fetchType=ISBN&fetchTypeValue=389283naknd")
//                .method("GET", null)
//                .build();
//        Response response = client.newCall(request).execute();
//        log.info(" response received {} ", response);
        String restTemplateResponse = restTemplate.getForObject("http://localhost:8080/book?fetchType=ISBN&fetchTypeValue=389283naknd", String.class);
        log.info(" response received {} ", restTemplateResponse);
    }
}
