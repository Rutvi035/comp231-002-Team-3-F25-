package com.luvo.model;

public class Trip {

    private Long id;
    private String title;
    private double price;
    private String details;
    private String priceRange;
    private String metaId; // optional id linking back to source entity (e.g., Tour.id)
    private String destination;
    private String type;
    private String imageUrl;

    public Trip() {}

    public Trip(String title, double price, String details, String destination, String type, String imageUrl) {
        this.id = (long) (Math.random() * 10000); // Just to simulate unique IDs
        this.title = title;
        this.price = price;
        this.details = details;
        this.destination = destination;
        this.type = type;
        this.imageUrl = imageUrl;
    }

    public Trip(String title, double price, String details, String destination, String type, String imageUrl, String priceRange) {
        this(title, price, details, destination, type, imageUrl);
        this.priceRange = priceRange;
    }

    // Getters and Setters
    public Long getId() { return id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getPriceRange() { return priceRange; }
    public void setPriceRange(String priceRange) { this.priceRange = priceRange; }

    public String getMetaId() { return metaId; }
    public void setMetaId(String metaId) { this.metaId = metaId; }

    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
    
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}
