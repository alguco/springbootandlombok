package com.springboot.lombok.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.lombok.exception.CustomerNotFoundException;
import com.springboot.lombok.model.Customer;
import com.springboot.lombok.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

// NOTE - We have left the implementation of HATEOAS principle for simplicity.

// Causes lombok to generate a logger field.
@Slf4j
@RestController
@RequestMapping(value = "/api/")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    // URL - http://localhost:10093/api/customers
    @GetMapping(value = "customers")
    public ResponseEntity<List<Customer>> getCustomers() {
        log.info("Getting all customers from the dB.");
        final Iterable<Customer> customerIterable = customerService.getAllCustomers();
        final List<Customer> customers = StreamSupport.stream(customerIterable.spliterator(), false).collect(Collectors.toList());
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    // URL - http://localhost:10093/api/customer/id/1
    @GetMapping(value = "customer/id/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable(name = "id") final int customerId) {
        log.info("Getting customer with customer-id= {} from the dB.", customerId);
        final Customer customer = customerService.getCustomerById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer with id= " + customerId + "not found in the dB."));
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    // URL - http://localhost:10093/api/customer/name/Mythopoeia
    @GetMapping(value = "customer/name/{name}")
    public ResponseEntity<List<Customer>> getCustomersByName(@PathVariable(name = "name") final String name) {
        log.info("Getting customer(s) for name= {} from the dB.", name);
        final List<Customer> customers = customerService.getAllCustomersByCustomerType(name);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    // URL - http://localhost:10093/api/customer/phone/52
    @GetMapping(value = "customer/phone/{phone}")
    public ResponseEntity<List<Customer>> getCustomersByPhoneGreaterThanEqual(
            @PathVariable(name = "phone") final int phone) {
        log.info("Getting customer(s) from the dB where phone is greater-than or equal to= {}.", phone);
        final List<Customer> customers = customerService.getAllCustomersByAmountGreaterThanEqual(phone);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    // URL - http://localhost:10093/api/customer/save
    // Sample HTTP POST request body.
    /*
    {
        "author": "Vasdev Mohi",
        "name": "Ghazals",
        "publisher": "Central Sahitya Akademi",
        "title": "Cheque customer",
        "phone": 1,
        "publishedOn": "2020-09-11T11:11:36Z"
    }
    */
    @PostMapping(value = "customer/save")
    public ResponseEntity<Void> save(@RequestBody final Customer customer) {
        log.info("Saving customer with details= {} in the dB.", customer.toString());
        customerService.save(customer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
