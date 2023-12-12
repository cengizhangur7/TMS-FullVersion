package com.estu.taskmanagement.controller;

import com.estu.taskmanagement.model.Subtask;
import com.estu.taskmanagement.service.SubtaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.List;


@Controller
public class SubtaskController {


    @Autowired
    private SubtaskService subtaskService;


        @GetMapping("/subtask")
        public List<Subtask> getSubtasks(){
            return subtaskService.getSubtasks();
        }

        @PostMapping("/subtask")
        public Subtask addSubtask(@RequestBody Subtask subtask) {
            return subtaskService.save(subtask);
        }

        @GetMapping("/subtask/{id}")
        public Subtask getById(@PathVariable Long id) {
            return subtaskService.getSubtaskById(id).orElseThrow(()->new EntityNotFoundException("Requested subtask not found!"));
        }

        @PutMapping("/subtask/{id}")
        public ResponseEntity<?> addSubtask(@RequestBody Subtask subtaskParameter, @PathVariable Long id) {
            if(subtaskService.existById(id)) {
                Subtask subtask=subtaskService.getSubtaskById(id).orElseThrow(()->new EntityNotFoundException("Requested subtask not found!"));
                subtask.setSubtaskName(subtaskParameter.getSubtaskName());
                subtask.setSubtaskDescription(subtaskParameter.getSubtaskDescription());
                subtask.setDueDate(subtaskParameter.getDueDate());
                subtask.setParentTask(subtaskParameter.getParentTask());
                subtask.setCompleted(subtaskParameter.isCompleted());

                subtaskService.save(subtask);
                return ResponseEntity.ok().body(subtask);
            }else {
                HashMap<String, String> message= new HashMap<>();
                message.put("message", "Subtask " + id + " not found or matched!");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
            }
        }

        @DeleteMapping("/subtask/{id}")
        public ResponseEntity<?> deleteTask(@PathVariable Long id) {
            if(subtaskService.existById(id)) {
                subtaskService.deleteSubtask(id);
                HashMap<String, String>message= new HashMap<>();
                message.put("message", "Subtask " + id + " removed!");
                return ResponseEntity.status(HttpStatus.OK).body(message);
            }else {
                HashMap<String, String>message= new HashMap<>();
                message.put("message", "Subtask " + id + " not found or matched!");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
            }
        }
}
