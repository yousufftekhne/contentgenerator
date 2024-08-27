package com.example.content_generator.dataservice.repository;

import com.example.content_generator.dataservice.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> { }
