package com.luvo.service;

import com.luvo.model.FamilyPlan;
import com.luvo.repository.IFamilyPlanRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FamilyPlannerService {

    private final IFamilyPlanRepository familyPlanRepository;

    public FamilyPlannerService(IFamilyPlanRepository familyPlanRepository) {
        this.familyPlanRepository = familyPlanRepository;
    }

    public List<FamilyPlan> getPlans() {
        return familyPlanRepository.findAll();
    }

    public FamilyPlan addPlan(FamilyPlan plan) {
        return familyPlanRepository.save(plan);
    }

    public FamilyPlan getPlan(String id) {
        return familyPlanRepository.findById(id).orElseThrow(() -> new RuntimeException("Plan not found"));
    }

    public FamilyPlan updatePlan(FamilyPlan plan) {
        return familyPlanRepository.save(plan);
    }

    public void deletePlan(String id) {
         familyPlanRepository.deleteById(id);
    }
}
