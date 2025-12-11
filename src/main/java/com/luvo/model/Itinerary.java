package com.luvo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID; 

@Document(collection = "itineraries")
public class Itinerary {

    @Id
    private String id;

    private String tripName;     // e.g. "Toronto Weekend"
    private String date;         // store as "yyyy-MM-dd" from the date input
    private List<ItineraryDay> days = new ArrayList<>();
    private double totalCost;

    public Itinerary() {
        this.id = UUID.randomUUID().toString();
    }

    // --- getters & setters ---

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<ItineraryDay> getDays() {
        return days;
    }

    public void setDays(List<ItineraryDay> days) {
        this.days = days;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    // Recalculate from all activities
    public void recalcTotalCost() {
        this.totalCost = days.stream()
                .flatMap(d -> d.getActivities().stream())
                .mapToDouble(ItineraryActivity::getCost)
                .sum();
    }

    // --------- inner classes for Day + Activity ----------

    public static class ItineraryDay {
        private int dayNumber; // 1, 2, 3...
        private List<ItineraryActivity> activities = new ArrayList<>();

        public int getDayNumber() {
            return dayNumber;
        }

        public void setDayNumber(int dayNumber) {
            this.dayNumber = dayNumber;
        }

        public List<ItineraryActivity> getActivities() {
            return activities;
        }

        public void setActivities(List<ItineraryActivity> activities) {
            this.activities = activities;
        }
    }

    public static class ItineraryActivity {
        private String id;
        private String time;      // "09:00"
        private String title;     // "Cafe Breakfast"
        private String location;  // "123 Main St"
        private double cost;

        public ItineraryActivity() {
            this.id = UUID.randomUUID().toString();
        }

        // getters & setters

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public double getCost() {
            return cost;
        }

        public void setCost(double cost) {
            this.cost = cost;
        }
    }
}



