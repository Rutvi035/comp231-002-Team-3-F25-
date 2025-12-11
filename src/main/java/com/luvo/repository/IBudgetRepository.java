package com.luvo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.luvo.model.Budget;

public interface IBudgetRepository extends MongoRepository<Budget, String> {
    
}
