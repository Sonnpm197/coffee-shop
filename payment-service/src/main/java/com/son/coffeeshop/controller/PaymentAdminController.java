package com.son.coffeeshop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentAdminController {

    @GetMapping("admin/test-payment")
    public String testOrder() {
        return "payment success";
    }
}
