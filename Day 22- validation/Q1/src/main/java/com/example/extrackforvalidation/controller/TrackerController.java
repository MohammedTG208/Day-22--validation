package com.example.extrackforvalidation.controller;

import com.example.extrackforvalidation.model.Tracker;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v2/tracker")
public class TrackerController {
    ArrayList<Tracker> trackers = new ArrayList<Tracker>();


    @GetMapping("/get/all")
    public ResponseEntity getAllTrackers() {
        if (trackers.isEmpty()){
            return ResponseEntity.status(400).body("trackers Empty");
        }else {
            return ResponseEntity.status(200).body(trackers);
        }
    }

    @PutMapping("/update/project/{id}")
    public ResponseEntity updateProject(@PathVariable int id, @Valid @RequestBody Tracker tracker, Errors errors) {
       if (errors.hasErrors()) {
           return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
       }else {
           for (int i = 0; i < trackers.size(); i++) {
               if (trackers.get(i).getId() == id) {
                   trackers.set(i, tracker);
                   return ResponseEntity.status(200).body("trackers Updated");
               }
           }
           return ResponseEntity.status(400).body("trackers Not Updated");
       }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteTracker(@PathVariable int id) {
        for (int i = 0; i <trackers.size() ; i++) {
            if (trackers.get(i).getId() == id) {
                trackers.remove(i);
                return ResponseEntity.status(200).body("trackers Deleted");
            }
        }
        return ResponseEntity.status(400).body("trackers Not Found Check the input");
    }

    @PutMapping("/status/{index}")
    public ResponseEntity updateStatus(@PathVariable int index) {
       for(Tracker tracker : trackers) {
           if (tracker.getId()==index) {
//               must be Not Started or in Progress or Completed only
               if (tracker.getStatus().equalsIgnoreCase("Not Started")){
                   tracker.setStatus("Progress");
                   return ResponseEntity.status(200).body("status Updated from Not Started to Progress");
               }else if (tracker.getStatus().equalsIgnoreCase("Progress")){
                   tracker.setStatus("Complete");
                   return ResponseEntity.status(200).body("status Updated from Progress to Complete");
               }else {
                   ResponseEntity.status(400).body("the status is Completed");
               }
           }
       }
        return ResponseEntity.status(400).body("trackers Not Found");
    }

    @GetMapping("/search/{title}")
    public ResponseEntity searchTracker(@PathVariable String title) {
       for (Tracker tracker : trackers) {
           if (tracker.getTitle().equalsIgnoreCase(title)) {
               return ResponseEntity.status(200).body(tracker);
           }
       }
       return ResponseEntity.status(400).body("trackers Not Found");
    }

    @GetMapping("/companyName/{companyName}")
    public ResponseEntity getCompanyName(@PathVariable String companyName) {
        for (Tracker tracker : trackers) {
            if (tracker.getCompanyName().equalsIgnoreCase(companyName)) {
                return ResponseEntity.status(200).body(tracker);
            }
        }
        return ResponseEntity.status(400).body("trackers Not Found");
    }

    @PostMapping("/add")
    public ResponseEntity addTracker(@Valid @RequestBody Tracker tracker, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }else {
            trackers.add(tracker);
            return ResponseEntity.status(200).body("trackers Added");
        }
    }

}

