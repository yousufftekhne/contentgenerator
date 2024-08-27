package com.example.content_generator.dataservice.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@JsonDeserialize
public class Behavioral {
    private String purchasingHabits;
    private String brandLoyalty;

    public String getPurchasingHabits() {
        return purchasingHabits;
    }

    public void setPurchasingHabits(String purchasingHabits) {
        this.purchasingHabits = purchasingHabits;
    }

    public String getBrandLoyalty() {
        return brandLoyalty;
    }

    public void setBrandLoyalty(String brandLoyalty) {
        this.brandLoyalty = brandLoyalty;
    }
}
