package com.luvo.service;

import com.luvo.model.Tour;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TourService {

    private final List<Tour> tours = new ArrayList<>();

    public List<Tour> getTours() {
        return tours;
    }

    public void addTour(Tour tour) {
        tours.add(tour);
    }

    public void deleteTour(int index) {
        if (index >= 0 && index < tours.size()) {
            tours.remove(index);
        }
    }

    // Prevent overlapping bookings on the same date/time
    public boolean hasOverlap(Tour newTour) {
        LocalDateTime newStart = LocalDateTime.parse(newTour.getStartTime());
        LocalDateTime newEnd   = LocalDateTime.parse(newTour.getEndTime());

        return tours.stream().anyMatch(existing -> {
            LocalDateTime exStart = LocalDateTime.parse(existing.getStartTime());
            LocalDateTime exEnd   = LocalDateTime.parse(existing.getEndTime());
            // overlap: start < existingEnd && end > existingStart
            return newStart.isBefore(exEnd) && newEnd.isAfter(exStart);
        });
    }
}
