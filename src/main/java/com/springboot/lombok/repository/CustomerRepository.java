package com.springboot.lombok.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.lombok.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    List<Customer> findCustomerByName(String name);

    List<Customer> findCustomerByphoneGreaterThanEqual(int phone);
}
