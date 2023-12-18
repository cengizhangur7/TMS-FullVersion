package com.estu.taskmanagement.controller;

import com.estu.taskmanagement.model.Task;
import com.estu.taskmanagement.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.List;

@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/task")
    public String getTasks(Model model) {
        List<Task> tasks = taskService.getTasks();
        model.addAttribute("tasks", tasks);
        return "task";
    }

    @PostMapping("/task")
    public String addTask(@ModelAttribute Task task, Model model) {
        taskService.save(task);
        List<Task> tasks = taskService.getTasks();
        model.addAttribute("tasks", tasks);
        return "redirect:/task"; // Redirect to the task page to refresh the table
    }


    @GetMapping("/task/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Task task = taskService.getTaskById(id)
                .orElseThrow(() -> new EntityNotFoundException("Requested Task not found!"));
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PutMapping("/task/{id}")
    public ResponseEntity<?> updateTask(@RequestBody Task updatedTask, @PathVariable Long id) {
        if (taskService.existsById(id)) {
            Task task = taskService.getTaskById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Requested Task not found!"));
            task.setTaskName(updatedTask.getTaskName());
            task.setTaskDescription(updatedTask.getTaskDescription());
            task.setTaskStatus(updatedTask.getTaskStatus());
            task.setDueDate(updatedTask.getDueDate());

            taskService.save(task);
            return ResponseEntity.ok().body(task);
        } else {
            HashMap<String, String> message = new HashMap<>();
            message.put("message", "Task " + id + " not found or matched!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }

    @DeleteMapping("/task/deleteTask")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        if (taskService.existsById(id)) {
            taskService.deleteTask(id);
            HashMap<String, String> message = new HashMap<>();
            message.put("message", "Task " + id + " removed!");
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } else {
            HashMap<String, String> message = new HashMap<>();
            message.put("message", "Task " + id + " not found or matched!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }
}
