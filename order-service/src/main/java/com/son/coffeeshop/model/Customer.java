package com.son.coffeeshop.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@RedisHash("customer")
public class Customer {

	@Id
	String id;
    String name;
    String contactName;
    String contactEmail;
    String contactPhone;
    
}
