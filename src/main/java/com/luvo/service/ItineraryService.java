package com.luvo.service;

import com.luvo.model.Itinerary;
import com.luvo.repository.ItineraryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
 
@Service
public class ItineraryService {

    private final ItineraryRepository repository;
 
    public ItineraryService(ItineraryRepository repository) {
        this.repository = repository;
    }

    public List<Itinerary> findAll() {
        return repository.findAll();
    }

    public Optional<Itinerary> findById(String id) {
        return repository.findById(id);
    }

    public Itinerary save(Itinerary itinerary) {
        itinerary.recalcTotalCost();
        return repository.save(itinerary);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }
}
