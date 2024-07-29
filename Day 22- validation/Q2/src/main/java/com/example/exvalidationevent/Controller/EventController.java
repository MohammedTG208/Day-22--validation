package com.example.exvalidationevent.Controller;

import com.example.exvalidationevent.model.Event;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class EventController {
    ArrayList<Event> evenSystems = new ArrayList<>();
    @GetMapping("/get")
    public ResponseEntity getAll() {
        return ResponseEntity.status(200).body(evenSystems);
    }


    @PostMapping("/add")
    public ResponseEntity addEvenSystem(@Valid @RequestBody Event evenSystem,Errors errors) {
        if (errors.hasErrors()) {
            System.out.println("dd");
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }else {
            evenSystems.add(evenSystem);
            return ResponseEntity.status(201).body("Added event successfully");
        }
    }

    @PutMapping("/update/event/{id}")
    public ResponseEntity updateEvenSystem(@PathVariable int id, @Valid @RequestBody Event evenSystem, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }else {
            if (id > evenSystems.size()) {
                return ResponseEntity.status(400).body("Invalid ID");
            } else {
                evenSystems.set(id, evenSystem);
                return ResponseEntity.status(200).body("Event updated successfully");
            }
        }
    }

    @DeleteMapping("/delete/{index}")
    public ResponseEntity deleteEvenSystem(@PathVariable int index) {
        if (index>evenSystems.size()) {
            return  ResponseEntity.status(400).body("Invalid Index");
        }else{
            evenSystems.remove(index-1);
            return ResponseEntity.status(200).body("Event deleted successfully");
        }
    }


    @PutMapping("/update/capacity/{index}/{capacity}")
    public ResponseEntity updateCapacity(@PathVariable int index, @PathVariable int capacity) {
        evenSystems.get(index-1).setCapacity(capacity);
        return ResponseEntity.status(200).body("Update successfully");
    }

    @GetMapping("/search/{id}")
    public ResponseEntity searchEvenSystem(@PathVariable int id) {
        return ResponseEntity.status(200).body(evenSystems.get(id-1));
    }


}
