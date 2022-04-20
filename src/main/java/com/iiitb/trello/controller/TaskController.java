package com.iiitb.trello.controller;

import com.iiitb.trello.model.dtos.TasksDto;
import com.iiitb.trello.model.entities.TaskEntity;
import com.iiitb.trello.services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @GetMapping(value="/api/get-tasks")
    public ResponseEntity<TasksDto> getTasks() {
        var tasks = taskService.getTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @PostMapping(value="/api/create-task")
    public Optional<TaskEntity> createTask(@RequestBody TaskEntity newTask) {
        return taskService.createTask(newTask);
    }

    @PutMapping(value="/api/edit-task")
    public Optional<TaskEntity> editTask(@RequestBody TaskEntity editedTask) {
        return taskService.editTask(editedTask);
    }

    @DeleteMapping(value="/api/delete-task/{taskId}")
    public Optional<TaskEntity> deleteTask(@PathVariable("taskId") Long taskId) {
        return taskService.deleteTask(taskId);
    }
}
