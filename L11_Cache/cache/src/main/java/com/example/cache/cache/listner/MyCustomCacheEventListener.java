//package com.example.cache.cache.listner;
//
//import lombok.extern.slf4j.Slf4j;
//import org.ehcache.event.CacheEvent;
//import org.ehcache.event.CacheEventListener;
//
//@Slf4j
//public class MyCustomCacheEventListener implements CacheEventListener<Object, Object> {
//
//
//    @Override
//    public void onEvent(CacheEvent<?, ?> cacheEvent) {
//        log.info(" event {} , key {} , value {} |||   old value{} ", cacheEvent.getType(), cacheEvent.getKey(),
//                cacheEvent.getNewValue(), cacheEvent.getOldValue());
//    }
//}
