package com.son.coffeeshop.controller;

import com.son.coffeeshop.configuration.CloudConfig;
import com.son.coffeeshop.filter.context.UserContext;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    final Logger logger = LoggerFactory.getLogger(GreetingController.class);

    @Autowired
    private CloudConfig cloudConfig;

    @GetMapping("hello")
    public String hello(HttpServletRequest request) {
        logger.info("this is the correlation id {}", request.getHeaders(UserContext.CORRELATION_ID));
        return cloudConfig.getProperty();
    }

    @GetMapping("test-payment")
    public String testPayment(HttpServletRequest request) {
        logger.info("this is the correlation id {}", request.getHeader(UserContext.CORRELATION_ID));
        return "Payment successfully";
    }
}
