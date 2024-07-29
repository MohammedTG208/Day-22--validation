package com.example.extrackforvalidation.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.slf4j.helpers.CheckReturnValue;

@Data
@AllArgsConstructor
public class Tracker {
    @NotNull(message = "Cannot be null")
    @Min(value = 2 , message = "Length more than 2")
    private int id;
    @NotEmpty(message = "Cannot be null ")
    @Min(message = "Length more than 8 ", value = 8)
    private String title;
    @NotEmpty(message = "Cannot be null ")
    @Min(value = 15 ,message = "Length more than 15 ")
    private String description;
    @NotEmpty(message = "Cannot be null")
//    @Pattern(regexp = "(?!\b=^.(Completed|Progress|Not Started)$).+b"",message = "must be 'Not Started' or in 'Progress' or 'Completed' only.")
    private String status;
    @NotEmpty(message = "Cannot be null ")
    @Min(value = 6, message = "Length more than 6")
    private String companyName;
}
