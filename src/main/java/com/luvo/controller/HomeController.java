package com.luvo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("pageTitle", "Luvo - Redefining Travel Experiences");
        return "index";
    }
    
    @GetMapping("/explore")
    public String explore(Model model) {
        model.addAttribute("pageTitle", "Explore Destinations");
        return "student-explorer";
    }
    
//    @GetMapping("/family-planner")
//    public String familyPlanner(Model model) {
//        model.addAttribute("pageTitle", "Family Planner");
//        return "family-planner";
//    }
    
    // @GetMapping("/business")
    // public String business(Model model) {
    //     model.addAttribute("pageTitle", "Local Business");
    //     return "business";
    // }
}