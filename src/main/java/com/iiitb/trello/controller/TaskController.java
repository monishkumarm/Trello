package com.iiitb.trello.controller;

import com.iiitb.trello.model.CustomUserDetails;
import com.iiitb.trello.model.dtos.BoardDto;
import com.iiitb.trello.model.entities.TaskEntity;
import com.iiitb.trello.services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @RequestMapping(value = "/getBoardsAll", method = RequestMethod.GET)
    public ResponseEntity<List<BoardDto>> getAllBoards() {
        var loggedInUserId = getLoggedInUserId();
        var boards = taskService.getAllBoards(loggedInUserId);
        return new ResponseEntity<>(boards, HttpStatus.OK);
    }

    @RequestMapping(value = "/getBoardDetail/{boardId}", method = RequestMethod.GET)
    public ResponseEntity<List<BoardDto>> getBoardDetail(@PathVariable Long boardId) {
        var loggedInUserId = getLoggedInUserId();
        var board = taskService.getBoardDetail(boardId, loggedInUserId);
        var boards = new ArrayList<BoardDto>();
        boards.add(board);
        return new ResponseEntity<>(boards, HttpStatus.OK);
    }

    @PostMapping(value = "/create-task")
    public Optional<TaskEntity> createTask(@RequestBody Map<String, Object> payload) {
        Long loggedInUserId = getLoggedInUserId();
        return taskService.createTask(payload, loggedInUserId);
    }

    @PostMapping(value = "/update-status")
    public Optional<TaskEntity> updateStatus(@RequestBody TaskEntity taskEntity) {
        return taskService.updateTaskStatus(taskEntity.getId(), taskEntity.getTaskStatusId());
    }

    @PutMapping(value = "/edit-task")
    public Optional<TaskEntity> editTask(@RequestBody TaskEntity editedTask) {
        return taskService.editTask(editedTask);
    }

    @DeleteMapping(value = "/delete-task/{taskId}")
    public Optional<TaskEntity> deleteTask(@PathVariable("taskId") Long taskId) {
        return taskService.deleteTask(taskId);
    }


    private Long getLoggedInUserId() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        var loggedInUser = (CustomUserDetails) auth.getPrincipal();
        return loggedInUser.getUserId();
    }
}
