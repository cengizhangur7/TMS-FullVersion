package com.estu.taskmanagement.controller;

import com.estu.taskmanagement.model.Task;
import com.estu.taskmanagement.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.List;


@Controller
public class TaskController {

        @Autowired
        private TaskService taskservice;


        @GetMapping("/task")
        public List<Task> getTask(){
            return taskservice.getTasks();
        }

        @PostMapping("/task")
        public Task addTask(@RequestBody Task task) {
            return taskservice.save(task);
        }

        @GetMapping("/task/{id}")
        public Task getById(@PathVariable Long id) {
            return taskservice.getTaskById(id).orElseThrow(()->new EntityNotFoundException("Requested Task not found!"));
        }

        @PutMapping("/task/{id}")
        public ResponseEntity<?> addTask(@RequestBody Task taskPara, @PathVariable Long id) {
            if(taskservice.existById(id)) {
                Task task=taskservice.getTaskById(id).orElseThrow(()->new EntityNotFoundException("Requested Task not found!"));
                task.setTaskName(taskPara.getTaskName());
                task.setTaskDescription(taskPara.getTaskDescription());
                task.setTaskStatus(taskPara.getTaskStatus());
                task.setDueDate(taskPara.getDueDate());

                taskservice.save(task);
                return ResponseEntity.ok().body(task);
            }else {
                HashMap<String, String> message= new HashMap<>();
                message.put("message", "Task "+ id + " not found or matched!");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
            }
        }

        @DeleteMapping("/task/{id}")
        public ResponseEntity<?> deleteTask(@PathVariable Long id) {
            if(taskservice.existById(id)) {
                taskservice.deleteTask(id);
                HashMap<String, String>message= new HashMap<>();
                message.put("message", "Task " + id + " removed!");
                return ResponseEntity.status(HttpStatus.OK).body(message);
            }else {
                HashMap<String, String>message= new HashMap<>();
                message.put("message", "Task "+ id + " not found or matched!");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
            }
        }
}
