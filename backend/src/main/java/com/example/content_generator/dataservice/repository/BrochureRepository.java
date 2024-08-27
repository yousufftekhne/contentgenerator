package com.example.content_generator.dataservice.repository;

import com.example.content_generator.dataservice.model.Brochure;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BrochureRepository extends MongoRepository<Brochure, String> {
    List<Brochure> findByProductId(String productId);
}
