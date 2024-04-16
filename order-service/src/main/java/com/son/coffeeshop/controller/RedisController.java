package com.son.coffeeshop.controller;

import com.son.coffeeshop.model.Customer;
import com.son.coffeeshop.repository.RedisCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("redis")
public class RedisController {

    @Autowired
    private RedisCustomerRepository redisCustomerRepository;

    @GetMapping("test")
    public String testRedis() {
        Customer customer = new Customer();
        customer.setName("abc");
        customer.setId("123");

        redisCustomerRepository.save(customer);

        return redisCustomerRepository.findById("123").get().getName();
    }
}
