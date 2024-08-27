package com.example.content_generator.dataservice.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import static com.example.content_generator.dataservice.core.Common.generateCustomUUID;

@JsonSerialize
@JsonDeserialize
@Document(collection = "Brochures")
public class Brochure {

    @Id
    private String id;
    private String productId;
    private String url;

    // Constructors
    public Brochure() {
        this.id = generateCustomUUID();
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
