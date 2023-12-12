package com.estu.taskmanagement.service;

import com.estu.taskmanagement.model.Subtask;
import com.estu.taskmanagement.repository.SubtaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class SubtaskService {

    private SubtaskRepository subtaskRepository;

    public List<Subtask> getSubtasks(){ return subtaskRepository.findAll();
    }

    public Subtask save(Subtask subtask) {
        return subtaskRepository.save(subtask);
    }
    public boolean existById(Long id){
        return subtaskRepository.existsById(id);
    }
    public Optional<Subtask> getSubtaskById(Long id) {
        return subtaskRepository.findById(id);
    }

    public void deleteSubtask(Long id) {  subtaskRepository.deleteById(id);}
}
