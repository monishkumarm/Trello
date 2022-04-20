package com.iiitb.trello.services;

import com.iiitb.trello.model.dtos.TasksDto;
import com.iiitb.trello.model.entities.TaskEntity;
import com.iiitb.trello.repo.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public TasksDto getTasks() {
        return new TasksDto(taskRepository.findAll());
    }

    public Optional<TaskEntity> createTask(TaskEntity newTask) {
        taskRepository.save(newTask);
        return taskRepository.findById(newTask.getId());
    }

    public Optional<TaskEntity> editTask(TaskEntity editedTask) {
        var task = taskRepository.getById(editedTask.getId());
        task.setName(editedTask.getName());
        //TODO to be modified
        return taskRepository.findById(editedTask.getId());
    }

    public Optional<TaskEntity> deleteTask(Long taskId) {
        var task = taskRepository.getById(taskId);
        task.setActive(false);
        return Optional.empty();
    }
}
