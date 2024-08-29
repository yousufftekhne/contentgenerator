package com.example.content_generator.dataservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DataServiceApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(DataServiceApplication.class, args);
        } catch (Exception e) {
            System.err.println("Application failed to start: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
