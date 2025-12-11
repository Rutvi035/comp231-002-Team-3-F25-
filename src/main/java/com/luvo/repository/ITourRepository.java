package com.luvo.repository;

import com.luvo.model.Tour;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITourRepository extends MongoRepository<Tour, String> {
}
