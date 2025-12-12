package com.luvo.model;

public class Tour {

    private String title;
    private String location;
    // store as ISO strings from <input type="datetime-local">
    private String startTime;
    private String endTime;
    private int capacity;

    public Tour() {}

    public Tour(String title, String location, String startTime, String endTime, int capacity) {
        this.title = title;
        this.location = location;
        this.startTime = startTime;
        this.endTime = endTime;
        this.capacity = capacity;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getStartTime() { return startTime; }
    public void setStartTime(String startTime) { this.startTime = startTime; }

    public String getEndTime() { return endTime; }
    public void setEndTime(String endTime) { this.endTime = endTime; }

    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
}
