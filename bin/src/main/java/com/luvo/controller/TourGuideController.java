package com.luvo.controller;

import com.luvo.model.Tour;
import com.luvo.service.TourService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/guide")
public class TourGuideController {

    private final TourService tourService;

    public TourGuideController(TourService tourService) {
        this.tourService = tourService;
    }

    // ---------- 1. Thymeleaf page ----------
    @GetMapping
    public String viewPage(Model model) {
        List<Tour> tours = tourService.getTours();
        model.addAttribute("pageTitle", "Luvo - Tour Guide");
        model.addAttribute("tours", tours);
        return "guide";      // renders guide.html
    }

    // ---------- 2. REST endpoints ----------
    @RestController
    @RequestMapping("/guide/api")
    public class TourGuideRest {

        @GetMapping
        public List<Tour> getTours() {
            return tourService.getTours();
        }

        @PostMapping
        public String addTour(@RequestBody Tour tour) {
            // basic validation
            if (tourService.hasOverlap(tour)) {
                return "DATE_CONFLICT";
            }
            tourService.addTour(tour);
            return "SUCCESS";
        }

        @DeleteMapping("/{index}")
        public void deleteTour(@PathVariable int index) {
            tourService.deleteTour(index);
        }
    }
}
