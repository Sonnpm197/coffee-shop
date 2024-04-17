package com.son.coffeeshop.controller;

import com.son.coffeeshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping("resiliency")
public class ResiliencyController {

    @Autowired
    private OrderService orderService;

    @GetMapping("circuit-breaker")
    public List<String> circuitBreaker() throws TimeoutException {
        return orderService.getOrders();
    }
}
