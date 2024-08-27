package com.example.content_generator.dataservice.repository;

import com.example.content_generator.dataservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> { }
