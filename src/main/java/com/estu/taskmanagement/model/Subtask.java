package com.estu.taskmanagement.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Subtask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subtaskName; // Changed field name from 'taskName' to 'subtaskName'
    private String subtaskDescription; // Changed field name from 'taskDescription' to 'subtaskDescription'
    private boolean isCompleted;

    @ManyToOne
    @JsonBackReference
    private Task parentTask; // Assuming a subtask belongs to a parent task

    @NotNull
    private LocalDate dueDate; // Using LocalDate for due date for better date handling

    // Constructors, getters, setters, etc.
}



