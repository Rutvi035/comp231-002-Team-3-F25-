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

    @GetMapping
    public String viewPage(Model model) {
        List<Tour> tours = tourService.getTours();
        model.addAttribute("pageTitle", "Luvo - Tour Guide");
        model.addAttribute("tours", tours);
        return "guide";
    }

    @RestController
    @RequestMapping("/guide/api")
    public class TourGuideRest {

        @GetMapping
        public List<Tour> getTours() {
            return tourService.getTours();
        }

        @PostMapping
        public String addTour(@RequestBody Tour tour) {
            if (tourService.hasOverlap(tour)) {
                return "DATE_CONFLICT";
            }
            tourService.addTour(tour);
            return "SUCCESS";
        }

        @DeleteMapping("/{id}")
        public void deleteTour(@PathVariable String id) {
            tourService.deleteTour(id);
        }
        
        @PostMapping("/{id}/book")
        public String bookTour(@PathVariable String id) {
            boolean ok = tourService.bookTour(id);
            return ok ? "BOOKED" : "NO_SEATS";
        }
    }
}
