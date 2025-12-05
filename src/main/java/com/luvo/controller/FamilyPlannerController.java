package com.luvo.controller;

import com.luvo.model.FamilyPlan;
import com.luvo.service.FamilyPlannerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequestMapping("/family-planner")
public class FamilyPlannerController {

    private final FamilyPlannerService plannerService;

    public FamilyPlannerController(FamilyPlannerService plannerService) {
        this.plannerService = plannerService;
    }

    // --- 1. Thymeleaf page ---
    @GetMapping
    public String viewPage(Model model) {
        List<FamilyPlan> plans = plannerService.getPlans();
        model.addAttribute("plans", plans);
        double totalBudget = plans.stream().mapToDouble(FamilyPlan::getBudget).sum();
        model.addAttribute("totalBudget", totalBudget);
        return "family-planner";
    }

    // --- 2. REST endpoints ---
    @RestController
    @RequestMapping("/family-planner/api")
    public class FamilyPlannerRest {

        @GetMapping
        public List<FamilyPlan> getPlans() {
            return plannerService.getPlans();
        }

        @PostMapping
        public FamilyPlan addPlan(@RequestBody FamilyPlan plan) {
            plannerService.addPlan(plan);
            return plan;
        }

        @PutMapping("/{index}/budget")
        public FamilyPlan updateBudget(@PathVariable int index, @RequestBody FamilyPlan budgetData) {
            FamilyPlan plan = plannerService.getPlans().get(index);
            plan.setFlights(budgetData.getFlights());
            plan.setLodging(budgetData.getLodging());
            plan.setFood(budgetData.getFood());
            plan.setActivities(budgetData.getActivities());
            plan.setBudget(plan.getFlights() + plan.getLodging() + plan.getFood() + plan.getActivities());
            return plan;
        }

        // ✅ DELETE endpoint
        @DeleteMapping("/{index}")
        public void deletePlan(@PathVariable int index) {
            List<FamilyPlan> plans = plannerService.getPlans();
            if (index >= 0 && index < plans.size()) {
                plans.remove(index);
            }
        }
    }
}