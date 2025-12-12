package com.luvo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Represents a family trip plan with a budget and categorized expenses.
 * Stored in MongoDB collection 'familyPlans'.
 */
@Document(collection = "familyPlans")
public class FamilyPlan {

    @Id
    private String id;

    private String tripName;
    private String status; // Planned, Booked, Completed

   
    private double totalBudget;

    
    private double flights;
    private double lodging;
    private double food;
    private double activities;

    public FamilyPlan() {
        // no-arg constructor required by Spring Data
    }

    public FamilyPlan(String tripName, double totalBudget) {
        this.tripName = tripName;
        this.totalBudget = totalBudget;
    }

    // getters / setters 
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTripName() {
        return tripName;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
        this.budget = budget;
        this.flights = 0;
        this.lodging = 0;
        this.food = 0;
        this.activities = 0;
    }

    
    public String getTripName() { return tripName; }
    public String getStatus() { return status; }
    public double getBudget() { return budget; }
    public double getFlights() { return flights; }
    public double getLodging() { return lodging; }
    public double getFood() { return food; }
    public double getActivities() { return activities; }

    public void setStatus(String status) { this.status = status; }
    public void setBudget(double budget) { this.budget = budget; }
    public void setFlights(double flights) { this.flights = flights; }
    public void setLodging(double lodging) { this.lodging = lodging; }
    public void setFood(double food) { this.food = food; }
    public void setActivities(double activities) { this.activities = activities; }
}
