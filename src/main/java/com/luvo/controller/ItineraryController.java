package com.luvo.controller;

import com.luvo.model.Itinerary;
import com.luvo.service.ItineraryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List; 

/**
 * Combined Controller:
 * - Serves the Thymeleaf Itinerary Page
 * - Provides REST API for CRUD operations
 */
@Controller
@RequestMapping
public class ItineraryController {

    private final ItineraryService itineraryService;

    public ItineraryController(ItineraryService itineraryService) {
        this.itineraryService = itineraryService;
    }

    // --------------------------------------
    // 🔹 1. THYMELEAF PAGE ROUTE (FRONTEND)
    // --------------------------------------

    @GetMapping("/itinerary")
    public String showItineraryPage(Model model) {
        model.addAttribute("pageTitle", "Itinerary Builder - Luvo");
        return "itinerary";   // loads templates/itinerary.html
    }


    // -------------------------------------------------------
    // 🔹 2. REST API ENDPOINTS (BACKEND CRUD FOR ITINERARIES)
    // -------------------------------------------------------

    @ResponseBody
    @GetMapping("/api/itineraries")
    public List<Itinerary> getAllItineraries() {
        return itineraryService.findAll();
    }

    @ResponseBody
    @GetMapping("/api/itineraries/{id}")
    public ResponseEntity<Itinerary> getItineraryById(@PathVariable String id) {
        return itineraryService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @ResponseBody
    @PostMapping("/api/itineraries")
    public ResponseEntity<Itinerary> createItinerary(@RequestBody Itinerary itinerary) {
        Itinerary saved = itineraryService.save(itinerary);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @ResponseBody
    @PutMapping("/api/itineraries/{id}")
    public ResponseEntity<Itinerary> updateItinerary(
            @PathVariable String id,
            @RequestBody Itinerary request) {

        return itineraryService.findById(id)
                .map(existing -> {
                    request.setId(existing.getId());
                    Itinerary updated = itineraryService.save(request);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @ResponseBody
    @DeleteMapping("/api/itineraries/{id}")
    public ResponseEntity<Void> deleteItinerary(@PathVariable String id) {
        itineraryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

