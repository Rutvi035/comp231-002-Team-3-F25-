package com.luvo.model;

public class FamilyPlan {
    private String tripName;
    private String status;
    private double budget; // total budget
    private double flights;
    private double lodging;
    private double food;
    private double activities;

    public FamilyPlan(String tripName, String status, double budget) {
        this.tripName = tripName;
        this.status = status;
        this.budget = budget;
        this.flights = 0;
        this.lodging = 0;
        this.food = 0;
        this.activities = 0;
    }

    // Getters & setters
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
