package com.luvo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.luvo.model.FamilyPlan;

public interface IFamilyPlanRepository extends MongoRepository<FamilyPlan, String> {
    
}
