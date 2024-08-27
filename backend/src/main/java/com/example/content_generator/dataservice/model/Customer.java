package com.example.content_generator.dataservice.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import static com.example.content_generator.dataservice.core.Common.generateCustomUUID;

@JsonSerialize
@JsonDeserialize
@Document(collection = "Customers")
public class Customer {

    @Id
    private String id;
    private String name;
    private Demographic demographic;
    private Physiographic physiographic;
    private Behavioral behavioral;
    private Location location;
    private DigitalBehavior digitalBehavior;

    // Constructors
    public Customer() {
        this.id = generateCustomUUID();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Demographic getDemographic() {
        return demographic;
    }

    public void setDemographic(Demographic demographic) {
        this.demographic = demographic;
    }

    public Physiographic getPsychographic() {
        return physiographic;
    }

    public void setPhysiographic(Physiographic physiographic) {
        this.physiographic = physiographic;
    }

    public Behavioral getBehavioral() {
        return behavioral;
    }

    public void setBehavioral(Behavioral behavioral) {
        this.behavioral = behavioral;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public DigitalBehavior getDigitalBehavior() {
        return digitalBehavior;
    }

    public void setDigitalBehavior(DigitalBehavior digitalBehavior) {
        this.digitalBehavior = digitalBehavior;
    }
}
