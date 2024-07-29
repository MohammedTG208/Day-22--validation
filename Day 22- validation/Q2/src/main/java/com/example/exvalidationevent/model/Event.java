package com.example.exvalidationevent.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Event {
//    ID , description , capacity, startDate , endDate
    @NotNull(message = "Cannot be null ")
    @Min(value = 2,message = "Length more than 2")
    private int id;
    @NotEmpty(message = "Cannot be null ")
    @Size(min = 15,message = "Length more than 15 ")
    private String description;

    @NotNull(message = "Cannot be null ")
    @Min(value = 25,message = "It must be more than 25")
//    @Pattern(regexp = "^[0-9]*$" ,message = "It has to be number")
    private int capacity;

    private LocalDate startDate;
    private LocalDate endDate;
}
