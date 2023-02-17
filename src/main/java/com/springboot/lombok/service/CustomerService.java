package com.springboot.lombok.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.lombok.model.Customer;
import com.springboot.lombok.repository.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository CustomerRepository;

    public void save(final Customer Customer) {
        CustomerRepository.save(Customer);
    }

    public long getCustomersCount() {
        return CustomerRepository.count();
    }

    public Iterable<Customer> getAllCustomers() {
        return CustomerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(final int CustomerId) {
        return CustomerRepository.findById(CustomerId);
    }

    public List<Customer> getAllCustomersByCustomerType(final String name) {
        return CustomerRepository.findCustomerByName(name);
    }

    public List<Customer> getAllCustomersByAmountGreaterThanEqual(final int phone) {
        return CustomerRepository.findCustomerByphoneGreaterThanEqual(phone);
    }
}
