package com.example.content_generator.dataservice.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@JsonSerialize
@JsonDeserialize
public class Price {

    @NotNull(message = "Currency cannot be null")
    @Pattern(regexp = "^[A-Z]{3}$", message = "Invalid currency code. It should be a 3-letter ISO 4217 code.")
    private String currencyCode;

    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    private double value;
}
