package com.example.L8.config;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    /**
     *
     *  WHy do we created a singleton instance for rest template --- ?
     *
     *          per instance there would be connection pooling
     *
     *
     *
     *          Certain properties of rest template a developer should be aware of
     *
     *     ConnectionPoll
     *              ------> is required ---> ??
     *               What happens in background
     *
     *
     *      Network -- >
     *      7 OSI Layers ---------
     *
     *     ---------------------------
     *
     *     (port) -- socket -->
     *      Application is running on a port and has exposed a socket
     *                      ---->
     *                      (mysql runns on 3306)
     *                      postgre 5432
     *
     *                      (one application) --- is interacting with other app
     *
     *                     TCP connection that is being established over the HTTP route
     *                     SpringbootApp    ------------> another app.
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *     DB -- DB connection Pooling
     *
     *
     *          -- stale connections
     *          --- active connections
     *
     *
     *
     *     HTTP - HttpConnection Pooling
     *
     *      -- default is 3
     *      Max per route -- HTTP route ---> localhost:8080
     *      total connections - no of all the connections that are present
     *      connection timeout =  timeout in tcp handshake
     *      socket timeout =   Timeout if data not received in a specified time limit (no data)
     *      Request timeout  = timeout if data is not read when socket has been established
     *
     *
     *
     *
     *
     *
     * @return
     */
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
