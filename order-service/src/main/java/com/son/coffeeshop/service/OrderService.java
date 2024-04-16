package com.son.coffeeshop.service;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeoutException;

@Service
public class OrderService {
    @CircuitBreaker(name = "order-service", fallbackMethod = "fallBack")
    @Bulkhead(name = "bulkhead-order-service", type= Bulkhead.Type.THREADPOOL, fallbackMethod = "fallBack")
    @Retry(name = "retry-order-service", fallbackMethod = "fallBack")
    public List<String> getOrders() throws TimeoutException {
        System.out.println("Getting order ......");
        randomlyRunLong();
        return Arrays.asList("Coffee", "Matcha", "Bean");
    }

    List<String> fallBack(Throwable t) {
        return Arrays.asList("this", "is", "fake");
    }

    private void randomlyRunLong() throws TimeoutException {
        sleep();
    }
    private void sleep() throws TimeoutException{
        try {
            System.out.println("Sleep");
            Thread.sleep(1000);
            throw new java.util.concurrent.TimeoutException();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
