package com.example.content_generator.dataservice.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@JsonDeserialize
public class DigitalBehavior {
    private String socialMediaUsage;
    private String browsingHabits;

    public String getSocialMediaUsage() {
        return socialMediaUsage;
    }

    public void setSocialMediaUsage(String socialMediaUsage) {
        this.socialMediaUsage = socialMediaUsage;
    }

    public String getBrowsingHabits() {
        return browsingHabits;
    }

    public void setBrowsingHabits(String browsingHabits) {
        this.browsingHabits = browsingHabits;
    }
}
