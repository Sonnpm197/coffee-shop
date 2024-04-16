package com.son.coffeeshop.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("payment-service")
public interface PaymentFeignClient {
    @RequestMapping(
            method= RequestMethod.GET,
            value="/test-payment",
            consumes="application/json")
    String testPayment();
}
