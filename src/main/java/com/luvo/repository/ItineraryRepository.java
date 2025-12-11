package com.luvo.repository;

import com.luvo.model.Itinerary;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItineraryRepository extends MongoRepository<Itinerary, String> {
    // default CRUD methods are enough
}

 