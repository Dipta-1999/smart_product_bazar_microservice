package com.microservice.orderservice.configuration;

import com.microservice.orderservice.service.serviceimpl.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OrderConfig {
//    @Bean
//    public OrderServiceImpl orderServiceImplBean()
//    {
//        OrderServiceImpl order = new OrderServiceImpl();
//        return new order;
//    }
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }
}
