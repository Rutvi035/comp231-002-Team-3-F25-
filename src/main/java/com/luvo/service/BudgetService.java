package com.luvo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luvo.model.Budget;
import com.luvo.repository.IBudgetRepository;

@Service
public class BudgetService {
    
    @Autowired
    private IBudgetRepository budgetRepository;

    public Budget saveBudget(Budget budget) {
        return budgetRepository.save(budget);
    }
}
