package com.son.coffeeshop.controller;

import com.son.coffeeshop.feign.PaymentFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("greeting")
public class GreetingController {

    @Autowired
    @Qualifier("restTemplate")
    private RestTemplate restTemplate;

    @Autowired
    @Qualifier("interceptRestTemplate")
    private RestTemplate interceptRestTemplate;

    @Autowired
    private PaymentFeignClient paymentFeignClient;

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("test-order")
    public String testOrder() {
        ResponseEntity<String> restExchange = restTemplate.exchange(
                "http://payment-service/test-payment",
                HttpMethod.GET,
                null, String.class);
        return restExchange.getBody();
    }

    @GetMapping("test-order-intercept")
    public String testOrderIntercept() {
        ResponseEntity<String> restExchange = interceptRestTemplate.exchange(
                "http://payment-service/test-payment",
                HttpMethod.GET,
                null, String.class);
        return restExchange.getBody();
    }


    @GetMapping("test-order-feign")
    public String testOrderFeign() {
        return paymentFeignClient.testPayment();
    }

    @GetMapping("test-order-discovery-client")
    public String testOrderDiscoveryClient() {
        RestTemplate restTemplate = new RestTemplate();
        List<ServiceInstance> instances = discoveryClient.getInstances("payment-service");
        if (instances.isEmpty()) return null;
        String serviceUri = String.format("%s/test-payment", instances.getFirst().getUri().toString());

        ResponseEntity<String> restExchange =
                restTemplate.exchange(
                        serviceUri,
                        HttpMethod.GET,
                        null, String.class);

        return restExchange.getBody();
    }

}
