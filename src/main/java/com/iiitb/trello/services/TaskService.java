package com.iiitb.trello.services;

import com.iiitb.trello.model.dtos.BoardDto;
import com.iiitb.trello.model.dtos.TaskDto;
import com.iiitb.trello.model.dtos.TaskStatusDto;
import com.iiitb.trello.model.entities.TaskAssigneeEntity;
import com.iiitb.trello.model.entities.TaskEntity;
import com.iiitb.trello.repo.BoardRepository;
import com.iiitb.trello.repo.TaskAssigneeRepository;
import com.iiitb.trello.repo.TaskRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskAssigneeRepository taskAssigneeRepository;
    private final BoardRepository boardRepository;

    public TaskService(TaskRepository taskRepository, TaskAssigneeRepository taskAssigneeRepository, BoardRepository boardRepository) {
        this.taskRepository = taskRepository;
        this.taskAssigneeRepository = taskAssigneeRepository;
        this.boardRepository = boardRepository;
    }

    public List<TaskDto> getTasks() {
        return taskRepository.findTasks();
    }

    public List<TaskDto> getTasksByBoardId(Long boardId) {
        return taskRepository.findTasksByBoardId(boardId);
    }

    public List<TaskStatusDto> getTaskStatuses() {
        var tasks = getTasks();
        var taskStatuses = taskRepository.findTaskStatuses();

        for (var taskStatus : taskStatuses) {
            var tasksStream = tasks.stream().filter((taskDto -> Objects.equals(taskDto.getTaskStatusId(), taskStatus.getId())));
            taskStatus.tasks = tasksStream.collect(Collectors.toList());
        }

        return taskStatuses;
    }

    public List<TaskStatusDto> getTaskStatusesByBoard(Long boardId) {
        var tasks = getTasksByBoardId(boardId);
        var taskStatuses = taskRepository.findTaskStatusesByBoardId(boardId);

        for (var taskStatus : taskStatuses) {
            var tasksStream = tasks.stream().filter((taskDto -> Objects.equals(taskDto.getTaskStatusId(), taskStatus.getId())));
            taskStatus.tasks = tasksStream.collect(Collectors.toList());
        }

        return taskStatuses;
    }


    public List<BoardDto> getAllBoards() {
        var boards = taskRepository.findBoards();
        return boards;
    }


    public BoardDto getBoardDetail(Long boardId) {
        var board = boardRepository.findBoardDetail(boardId);
        board.taskStatuses = getTaskStatusesByBoard(boardId);
        return board;
    }

    public Optional<TaskEntity> createTask(Map<String, Object> payload, Long loggedInUserId) {
        var newTask = new TaskEntity();
        newTask.setName((String) payload.get("name"));
        newTask.setDescription((String) payload.get("description"));

        var boardId = (Integer) payload.get("boardId");
        var taskStatusId = (Integer) payload.get("taskStatusId");
        newTask.setBoardId(Long.valueOf(boardId));
        newTask.setTaskStatusId(Long.valueOf(taskStatusId));

        newTask.setCreatedBy(loggedInUserId);
        newTask.setActive(true);
        newTask.setCreatedBy(loggedInUserId);
        newTask.setCreatedOn(Timestamp.from(Instant.now()));
        newTask.setLastChangeBy(loggedInUserId);
        newTask.setLastChangeOn(Timestamp.from(Instant.now()));

        TaskEntity savedEntity = taskRepository.save(newTask);

        var assignees = (ArrayList<Integer>) payload.get("assignees");

        for (Integer assigneeId : assignees) {
            var taskAssigneeEntity = new TaskAssigneeEntity();
            taskAssigneeEntity.setTaskId(savedEntity.getId());
            taskAssigneeEntity.setUserId(Long.valueOf(assigneeId));
            taskAssigneeRepository.save(taskAssigneeEntity);
        }

        return taskRepository.findById(newTask.getId());
    }

    public Optional<TaskEntity> updateTaskStatus(Long taskId, Long taskStatusId) {
        var task = taskRepository.getById(taskId);
        task.setTaskStatusId(taskStatusId);
        taskRepository.save(task);
        return Optional.of(task);
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
