package com.example.cache.cache.config;

import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Configuration
public class RedisConfig {


    /**
     * TTL
     */
//    @Bean
//    RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer(){
//        return builder -> builder.withCacheConfiguration("booksCache",
//                RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(2)));
//    }


//#spring.data.redis.client-type=lettuce
//#spring.data.redis.port=10019
//            #spring.data.redis.host=redis-10019.c264.ap-south-1-1.ec2.cloud.redislabs.com
//#spring.data.redis.password=hEYtmJ5TO4d1q0oaW2V9hNVFWKVxz5Qz

    @Bean
    LettuceConnectionFactory lettuceConnectionFactory(){
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setPort(10019);
        configuration.setHostName("redis-10019.c264.ap-south-1-1.ec2.cloud.redislabs.com");
        configuration.setPassword("hEYtmJ5TO4d1q0oaW2V9hNVFWKVxz5Qz");
        return new LettuceConnectionFactory(configuration);
    }


    @Bean
    RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory lettuceConnectionFactory){
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
        return redisTemplate;
    }



}
