package com.iiitb.trello.controller;

import com.iiitb.trello.model.dtos.BoardDto;
import com.iiitb.trello.model.entities.TaskEntity;
import com.iiitb.trello.services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }


    @RequestMapping(value="/getBoards", method = RequestMethod.GET)
    public ResponseEntity<List<BoardDto>> getBoards() {
        var boards = taskService.getBoards();
        return new ResponseEntity<>(boards, HttpStatus.OK);
    }

    @PostMapping(value="/create-task")
    public Optional<TaskEntity> createTask(@RequestBody TaskEntity newTask) {
        return taskService.createTask(newTask);
    }

    @PutMapping(value="/edit-task")
    public Optional<TaskEntity> editTask(@RequestBody TaskEntity editedTask) {
        return taskService.editTask(editedTask);
    }

    @DeleteMapping(value="/delete-task/{taskId}")
    public Optional<TaskEntity> deleteTask(@PathVariable("taskId") Long taskId) {
        return taskService.deleteTask(taskId);
    }
}
