package com.luvo.service;

import com.luvo.model.FamilyPlan;
import com.luvo.model.Budget;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FamilyPlannerService {

    private final List<FamilyPlan> plans = new ArrayList<>();
    private Budget budget = new Budget();

    public List<FamilyPlan> getPlans() {
        return plans;
    }

    public void addPlan(FamilyPlan plan) {
        plans.add(plan);
    }

    public Budget getBudget() {
        return budget;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }
}
