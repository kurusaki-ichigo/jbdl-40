package com.example.kafka.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import static com.example.kafka.kafka.KafkaApplication.KAFKA_TOPIC;

@Component
@Slf4j
public class SampleConsumer {



//    @KafkaListener(topics = {KAFKA_TOPIC}, groupId = "sample_group")
//    public void printMessage(@Payload String message){
//        log.info("message -----------> received ------------> {}", message);
//    }


    @KafkaListener(topics = {KAFKA_TOPIC}, groupId = "consumer_group")
    public void printMessageWithConsumerRecord(@Payload ConsumerRecord<String , String> consumerRecord){
        log.info(" consumer {} {} message -----------> received ------------> {}", consumerRecord.partition(),
                consumerRecord.offset(), consumerRecord.value());
    }

}
