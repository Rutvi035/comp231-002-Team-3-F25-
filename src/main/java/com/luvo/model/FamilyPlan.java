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
    private String status; // e.g. Planned, Booked, Completed

    // Total budget for the trip
    private double totalBudget;

    // Detailed expenses
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

    // --------- Basic getters / setters ---------
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
    }

    public double getTotalBudget() {
        return totalBudget;
    }

    public void setTotalBudget(double totalBudget) {
        this.totalBudget = totalBudget;
    }

    public double getFlights() {
        return flights;
    }

    public void setFlights(double flights) {
        this.flights = normalize(flights);
    }

    public double getLodging() {
        return lodging;
    }

    public void setLodging(double lodging) {
        this.lodging = normalize(lodging);
    }

    public double getFood() {
        return food;
    }

    public void setFood(double food) {
        this.food = normalize(food);
    }

    public double getActivities() {
        return activities;
    }

    public void setActivities(double activities) {
        this.activities = normalize(activities);
    }

    // --------- Convenience helpers ---------

    /**
     * Returns total spent across all expense categories.
     */
    public double getTotalSpent() {
        return flights + lodging + food + activities;
    }

    /**
     * Returns remaining budget (totalBudget - totalSpent).
     */
    public double getRemaining() {
        return totalBudget - getTotalSpent();
    }

    /**
     * Returns true when expenses exceed the allocated total budget.
     */
    public boolean isOverBudget() {
        return getTotalSpent() > totalBudget;
    }

    /**
     * Add an expense amount to a named category. Valid categories: flights, lodging, food, activities.
     * If category is unrecognized, no change is applied.
     */
    public void addExpense(String category, double amount) {
        double a = normalize(amount);
        switch (category == null ? "" : category.trim().toLowerCase()) {
            case "flights" -> this.flights += a;
            case "lodging" -> this.lodging += a;
            case "food" -> this.food += a;
            case "activities" -> this.activities += a;
            default -> {
                // unknown category — ignore (controller/view should validate)
            }
        }
    }

    /**
     * Replace the expense value for a named category.
     */
    public void setExpense(String category, double amount) {
        double a = normalize(amount);
        switch (category == null ? "" : category.trim().toLowerCase()) {
            case "flights" -> this.flights = a;
            case "lodging" -> this.lodging = a;
            case "food" -> this.food = a;
            case "activities" -> this.activities = a;
            default -> {
            }
        }
    }

    private double normalize(double v) {
        if (Double.isNaN(v) || Double.isInfinite(v) || v < 0) return 0d;
        return Math.round(v * 100.0) / 100.0; // keep cents precision
    }

    @Override
    public String toString() {
        return "FamilyPlan{" +
                "id='" + id + '\'' +
                ", tripName='" + tripName + '\'' +
                ", totalBudget=" + totalBudget +
                ", totalSpent=" + getTotalSpent() +
                '}';
    }
}
