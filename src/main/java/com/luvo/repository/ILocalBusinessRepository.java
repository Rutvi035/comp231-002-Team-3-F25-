package com.luvo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.luvo.model.LocalBusiness;

public interface ILocalBusinessRepository extends MongoRepository<LocalBusiness, String> {
    
}
