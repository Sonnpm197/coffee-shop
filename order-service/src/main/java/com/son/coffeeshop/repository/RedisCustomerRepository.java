package com.son.coffeeshop.repository;

import com.son.coffeeshop.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedisCustomerRepository extends CrudRepository<Customer,String> {
}
