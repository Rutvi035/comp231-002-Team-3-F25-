package com.luvo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.luvo.model.Trip;


@Service
public class StudentExplorerService {

    private final List<Trip> trips = new ArrayList<>();

    public StudentExplorerService() {
        // Simulated trip data
        trips.add(new Trip("Paris Getaway", 1200, "5-night • 4.5 star", "Paris", "City", "images/paris.jpg"));
        trips.add(new Trip("Cancun Trip", 850, "4-night • 4.4 star", "Cancun", "Beach", "images/cancun.jpg"));
        trips.add(new Trip("Toronto City Tour", 600, "2-night • 4.2 City", "Toronto", "City", "images/toronto.jpg"));
        trips.add(new Trip("Rocky Mountains Hike", 950, "5-night • 4.8 mountains", "Alberta", "Mountains", "images/rocky_mountains.jpg"));
    }

    public List<Trip> getTrips(String destination, String type) {
        return trips.stream()
                .filter(t -> destination == null || destination.isEmpty() || t.getDestination().toLowerCase().contains(destination.toLowerCase()))
                .filter(t -> type == null || type.equals("Any type") || t.getType().equalsIgnoreCase(type))
                .toList();
    }

    public Trip getTrip(Long id) {
        return trips.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Trip not found"));
    }
}