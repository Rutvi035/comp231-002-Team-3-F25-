package com.luvo.model;

public class Budget {
    private double flights = 0;
    private double lodging = 0;
    private double food = 0;
    private double activities = 0;

    public Budget() {}
    public Budget(double flights, double lodging, double food, double activities) {
        this.flights = flights;
        this.lodging = lodging;
        this.food = food;
        this.activities = activities;
    }

    public double getFlights() { return flights; }
    public double getLodging() { return lodging; }
    public double getFood() { return food; }
    public double getActivities() { return activities; }

    public void setFlights(double flights) { this.flights = flights; }
    public void setLodging(double lodging) { this.lodging = lodging; }
    public void setFood(double food) { this.food = food; }
    public void setActivities(double activities) { this.activities = activities; }

    public double getTotal() {
        return flights + lodging + food + activities;
    }
}
