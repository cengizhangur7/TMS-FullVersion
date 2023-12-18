package com.estu.taskmanagement.service;

import com.estu.taskmanagement.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    List<Task> getTasks();
    Optional<Task> getTaskById(Long id);
    Task save(Task task);
    void deleteTask(Long id);
    boolean existsById(Long id);
}
