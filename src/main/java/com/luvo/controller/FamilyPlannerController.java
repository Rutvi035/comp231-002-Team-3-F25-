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

    
    @GetMapping
    public String viewPage(Model model) {
        List<FamilyPlan> plans = plannerService.getPlans();
        model.addAttribute("plans", plans);
        double totalBudget = plans.stream().mapToDouble(FamilyPlan::getTotalBudget).sum();
        model.addAttribute("totalBudget", totalBudget);
        return "family-planner";
    }

   
    @RestController
    @RequestMapping("/family-planner/api")
    public class FamilyPlannerRest {

        @GetMapping
        public List<FamilyPlan> getPlans() {
            return plannerService.getPlans();
        }

        @PostMapping
        public FamilyPlan addPlan(@RequestBody FamilyPlan plan) {
            return plannerService.addPlan(plan);
        }

        @PutMapping("/{id}/budget")
        public FamilyPlan updateBudget(@PathVariable String id, @RequestBody FamilyPlan budgetData) {
            FamilyPlan plan = plannerService.getPlan(id);
            plan.setFlights(budgetData.getFlights());
            plan.setLodging(budgetData.getLodging());
            plan.setFood(budgetData.getFood());
            plan.setActivities(budgetData.getActivities());

            return plannerService.updatePlan(plan);
        }

        // DELETE endpoint by id
            // Recalculate total spent into the stored totalBudget if desired, or keep totalBudget as allocation.
            // Here we keep totalBudget as the allocation; caller may update allocation separately.
            return plannerService.updatePlan(plan);
        }

        
        @DeleteMapping("/{index}")
        public void deletePlan(@PathVariable int index) {
            List<FamilyPlan> plans = plannerService.getPlans();
            if (index >= 0 && index < plans.size()) {
                plans.remove(index);
            }
        }
    }
}
