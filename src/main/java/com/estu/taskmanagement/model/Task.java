package com.estu.taskmanagement.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String taskName;

    @NotBlank
    private String taskDescription;

    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus; // TaskStatus is an enum representing completion status


    @NotNull
    private LocalDate dueDate;

    @OneToMany(mappedBy = "parentTask", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private List<Subtask> subtasks;

    @ManyToOne
    @JoinColumn(name = "assigned_user_id") // Specify the name of the foreign key column
    @JsonBackReference
    private User assignedUser;

    // Constructors, getters, setters, etc.
}

