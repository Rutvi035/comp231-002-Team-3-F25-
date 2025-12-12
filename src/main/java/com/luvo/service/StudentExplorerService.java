package com.luvo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.luvo.model.Trip;
import com.luvo.model.LocalBusiness;
import com.luvo.model.Tour;

@Service
public class StudentExplorerService {

    private final List<Trip> trips = new ArrayList<>();
    private final LocalBusinessService localBusinessService;
    private final TourService tourService;

    public StudentExplorerService(LocalBusinessService localBusinessService, TourService tourService) {
        this.localBusinessService = localBusinessService;
        this.tourService = tourService;

        // Simulated trip data
        trips.add(new Trip("Paris Getaway", 1200, "5-night • 4.5 star", "Paris", "City", "images/paris.jpg"));
        trips.add(new Trip("Cancun Trip", 850, "4-night • 4.4 star", "Cancun", "Beach", "images/cancun.jpg"));
        trips.add(new Trip("Toronto City Tour", 600, "2-night • 4.2 City", "Toronto", "City", "images/toronto.jpg"));
        trips.add(new Trip("Rocky Mountains Hike", 950, "5-night • 4.8 mountains", "Alberta", "Mountains",
                "images/rocky_mountains.jpg"));
    }

    // Main filter method for Student Explorer

    public List<Trip> getTrips(String destination, String type) {

        // LocalBusiness mapped to Trip
        if (type != null && (type.equalsIgnoreCase("Hotel") || type.equalsIgnoreCase("Airbnb"))) {
            return localBusinessService.findAll().stream()
                    .filter(b -> b.getCategory() != null &&
                            (b.getCategory().equalsIgnoreCase("Hotel") ||
                                    b.getCategory().equalsIgnoreCase("Airbnb")))
                    .filter(b -> destination == null || destination.isEmpty() ||
                            (b.getName() != null && b.getName().toLowerCase().contains(destination.toLowerCase())) ||
                            (b.getDescription() != null
                                    && b.getDescription().toLowerCase().contains(destination.toLowerCase())))
                    .map(this::businessToTrip)
                    .collect(Collectors.toList());
        }

        // Tours mapped to Trip
        if (type != null && type.equalsIgnoreCase("Activity")) {
            return tourService.getTours().stream()
                    .map(this::tourToTrip)
                    .filter(t -> destination == null || destination.isEmpty() ||
                            (t.getDestination() != null
                                    && t.getDestination().toLowerCase().contains(destination.toLowerCase()))
                            ||
                            (t.getDetails() != null
                                    && t.getDetails().toLowerCase().contains(destination.toLowerCase())))
                    .collect(Collectors.toList());
        }

        // Sample trips / all tours

        List<Trip> results = new ArrayList<>();

        // Add sample trips
        results.addAll(trips.stream()
                .filter(t -> destination == null || destination.isEmpty() ||
                        t.getDestination().toLowerCase().contains(destination.toLowerCase()))
                .filter(t -> type == null || type.equals("Any type") ||
                        t.getType().equalsIgnoreCase(type))
                .collect(Collectors.toList()));

        // Add tours mapped to Trip
        results.addAll(tourService.getTours().stream()
                .map(this::tourToTrip)
                .filter(t -> destination == null || destination.isEmpty() ||
                        (t.getDestination() != null
                                && t.getDestination().toLowerCase().contains(destination.toLowerCase()))
                        ||
                        (t.getDetails() != null && t.getDetails().toLowerCase().contains(destination.toLowerCase())))
                .collect(Collectors.toList()));

        return results;
    }

    // Returns a Trip by ID from the sample list.

    public Trip getTrip(Long id) {
        return trips.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Trip not found"));
    }

    // Convert LocalBusiness to Trip object
    private Trip businessToTrip(LocalBusiness b) {
        String title = b.getName() == null ? "Hotel" : b.getName();
        double price = parsePriceRange(b.getPriceRange());
        String details = b.getDescription() == null ? b.getCategory() : b.getDescription();
        String destination = b.getName();
        String type = "Hotel";
        String imageUrl = b.getImagePath() != null ? b.getImagePath() : "images/hotel_placeholder.jpg";

        Trip trip = new Trip(title, price, details, destination, type, imageUrl);
        trip.setPriceRange(b.getPriceRange());
        return trip;
    }

    // Convert Tour to Trip object (Activity)
    private Trip tourToTrip(Tour t) {
        String title = t.getTitle() == null ? "Activity" : t.getTitle();
        double price = 0;
        String details = t.getLocation() == null ? "Activity" : t.getLocation();
        String destination = t.getLocation();
        String type = "Activity";
        String imageUrl = "images/activity_placeholder.jpg";

        Trip trip = new Trip(title, price, details, destination, type, imageUrl);
        trip.setMetaId(t.getId());
        return trip;
    }

    // Converts priceRange into numeric estimated price.

    private double parsePriceRange(String pr) {
        if (pr == null || pr.isBlank())
            return 0.0;
        try {
            String s = pr.trim();

            // Extract numbers if present
            java.util.regex.Matcher numMatcher = java.util.regex.Pattern.compile("\\d+(?:\\.\\d+)?").matcher(s);

            List<Double> nums = new ArrayList<>();
            while (numMatcher.find())
                nums.add(Double.parseDouble(numMatcher.group()));
            if (!nums.isEmpty()) {
                double sum = 0;
                for (double d : nums)
                    sum += d;
                return sum / nums.size();
            }

            // Fallback to $ count
            java.util.regex.Matcher dollarMatcher = java.util.regex.Pattern
                    .compile("\\$+", java.util.regex.Pattern.CASE_INSENSITIVE).matcher(s);

            List<Integer> counts = new ArrayList<>();
            while (dollarMatcher.find())
                counts.add(dollarMatcher.group().length());

            if (!counts.isEmpty()) {
                double sum = 0;
                for (int c : counts) {
                    switch (c) {
                        case 1:
                            sum += 50;
                            break;
                        case 2:
                            sum += 100;
                            break;
                        case 3:
                            sum += 200;
                            break;
                        default:
                            sum += 100;
                    }
                }
                return sum / counts.size();
            }

            // Keyword fallback
            String low = s.toLowerCase();
            if (low.contains("cheap") || low.contains("budget") || low.contains("low"))
                return 50.0;
            if (low.contains("expensive") || low.contains("luxury") || low.contains("high"))
                return 200.0;

            return 0.0;

        } catch (Exception e) {
            return 0.0;
        }
    }
}
