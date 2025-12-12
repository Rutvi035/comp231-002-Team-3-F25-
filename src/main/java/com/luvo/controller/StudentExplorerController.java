package com.luvo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luvo.model.Trip;
import com.luvo.service.StudentExplorerService;


@RestController
@RequestMapping("/explore/api") // Base path for all endpoints
@CrossOrigin("*") 
public class StudentExplorerController {

    private final StudentExplorerService service;

    public StudentExplorerController(StudentExplorerService service) {
        this.service = service;
    }

    @GetMapping  // List trips with optional filters
    public List<Trip> getTrips(@RequestParam(required = false) String destination,
                               @RequestParam(required = false) String type) {
        return service.getTrips(destination, type);
    }

    @GetMapping("/{id}")
    public Trip getTrip(@PathVariable Long id) {
        return service.getTrip(id);
    }
}
