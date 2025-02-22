package com.example.content_generator.dataservice.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.content_generator.dataservice.model.Customer;
import com.example.content_generator.dataservice.model.Product;
import com.example.content_generator.dataservice.repository.CustomerRepository;
import com.example.content_generator.dataservice.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class DataAppendService {
    @Value("${data.append.enabled}")
    private boolean appendDataEnabled;

    @Value("${data.product.path}")
    private String productJsonFilePath;

    @Value("${data.customer.path}")
    private String customerJsonFilePath;

    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final ObjectMapper objectMapper;

    public DataAppendService(CustomerRepository customerRepository ,ProductRepository productRepository, ObjectMapper objectMapper) {
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.objectMapper = objectMapper;
    }

    @Async
    public void saveProductsToMongoDB() {
        try {
            File productJsonFile = new File(productJsonFilePath);
            List<Product> products = objectMapper.readValue(productJsonFile, new TypeReference<List<Product>>() {});
            productRepository.saveAll(products);
            System.out.println("Products appended to MongoDB successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        CompletableFuture.completedFuture(null);
    }

    @Async
    public void saveCustomersToMongoDB() {
        try {
            File customerJsonFile = new File(customerJsonFilePath);
            List<Customer> customers = objectMapper.readValue(customerJsonFile, new TypeReference<List<Customer>>() {});
            customerRepository.saveAll(customers);
            System.out.println("Customers appended to MongoDB successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        CompletableFuture.completedFuture(null);
    }


    @PostConstruct
    public void appendDataToMongoDB() {
        if (appendDataEnabled) {
            try {
                saveProductsToMongoDB();
                saveCustomersToMongoDB();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Data append is disabled.");
        }
    }
}
