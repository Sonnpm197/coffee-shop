package com.son.coffeeshop.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class AuthorizationController {

    private static final Logger logger = LoggerFactory.getLogger(AuthorizationController.class);


    @Autowired
    @Qualifier("restTemplate")
    private RestTemplate restTemplate;

    @Autowired
    @Qualifier("interceptRestTemplate")
    private RestTemplate interceptRestTemplate;

    @Autowired
    HttpServletRequest request;

    @Autowired
    @Qualifier("restTemplateTraceId")
    private RestTemplate restTemplateTraceId;

    @GetMapping("admin/test-order")
    public String adminTestOrder() {
        logger.info("send to admin/test-order");
        ResponseEntity<String> restExchange = restTemplateTraceId.exchange(
                "http://payment-service:8082/admin/test-payment",
                HttpMethod.GET,
                null, String.class);
        return restExchange.getBody();
    }


}
