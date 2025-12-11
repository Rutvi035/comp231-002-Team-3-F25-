package com.luvo.service;

import com.luvo.model.Tour;
import com.luvo.repository.ITourRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
public class TourService {

    private final ITourRepository repo;

    public TourService(ITourRepository repo) {
        this.repo = repo;
    }

    public List<Tour> getTours() {
        return repo.findAll();
    }

    public Tour addTour(Tour tour) {
        return repo.save(tour);
    }

    public void deleteTour(String id) {
        repo.deleteById(id);
    }

    // Book one seat on a tour; returns true if booked, false if full or not found
    public boolean bookTour(String id) {
        return repo.findById(id).map(t -> {
            if (t.getCapacity() <= 0) return false;
            t.setCapacity(t.getCapacity() - 1);
            repo.save(t);
            return true;
        }).orElse(false);
    }

    // Prevent overlapping bookings on the same date/time
    public boolean hasOverlap(Tour newTour) {
        try {
            LocalDateTime newStart = LocalDateTime.parse(newTour.getStartTime());
            LocalDateTime newEnd = LocalDateTime.parse(newTour.getEndTime());

            return repo.findAll().stream().anyMatch(existing -> {
                try {
                    LocalDateTime exStart = LocalDateTime.parse(existing.getStartTime());
                    LocalDateTime exEnd = LocalDateTime.parse(existing.getEndTime());
                    return newStart.isBefore(exEnd) && newEnd.isAfter(exStart);
                } catch (DateTimeParseException e) {
                    return false;
                }
            });
        } catch (DateTimeParseException e) {
            return true; // invalid date -> treat as conflict to be safe
        }
    }
}
