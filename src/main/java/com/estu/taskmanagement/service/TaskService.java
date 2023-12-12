package com.estu.taskmanagement.service;

import com.estu.taskmanagement.model.Task;
import com.estu.taskmanagement.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class TaskService {

    private TaskRepository taskRepository;

    public List<Task> getTasks(){
        return taskRepository.findAll();
    }

    public Task save(Task task) {
        return taskRepository.save(task);
    }
    public boolean existById(Long id){
        return taskRepository.existsById(id);
    }
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public void deleteTask(Long id) {  taskRepository.deleteById(id);}
}
