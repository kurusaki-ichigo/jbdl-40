package com.example.kafka.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import static com.example.kafka.kafka.KafkaApplication.KAFKA_TOPIC;

@Component
@Slf4j
public class SampleProducer implements InitializingBean {


    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;


    @Override
    public void afterPropertiesSet() throws Exception {
        String HashKey = "random";
        for (int i = 0 ; i < 10 ; i++){
            String message = " ---> Hey , I was doing just fine before I met you" + i;
            log.info(" sending message {} ", message);
            kafkaTemplate.send(KAFKA_TOPIC,HashKey ,message).get();
            log.info("message sent {} ", message);
        }
    }
}
