package com.iiitb.trello.services;

import com.iiitb.trello.model.dtos.BoardDto;
import com.iiitb.trello.model.dtos.TaskDto;
import com.iiitb.trello.model.dtos.TaskStatusDto;
import com.iiitb.trello.model.entities.TaskAssigneeEntity;
import com.iiitb.trello.model.entities.TaskEntity;
import com.iiitb.trello.model.entities.UserPermissionBoardEntity;
import com.iiitb.trello.repo.BoardRepository;
import com.iiitb.trello.repo.TaskAssigneeRepository;
import com.iiitb.trello.repo.TaskRepository;
import com.iiitb.trello.repo.UserPermissionBoardRepository;
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
    private final UserPermissionBoardRepository userPermissionBoardRepository;

    public TaskService(TaskRepository taskRepository, TaskAssigneeRepository taskAssigneeRepository, BoardRepository boardRepository, UserPermissionBoardRepository userPermissionBoardRepository) {
        this.taskRepository = taskRepository;
        this.taskAssigneeRepository = taskAssigneeRepository;
        this.boardRepository = boardRepository;
        this.userPermissionBoardRepository = userPermissionBoardRepository;
    }

    public List<TaskDto> getTasksByBoardId(Long boardId, Long loggedInUserId) {
        return taskRepository.findTasksByBoardId(boardId, loggedInUserId);
    }

    public List<TaskStatusDto> getTaskStatusesByBoard(Long boardId, Long loggedInUserId) {
        var tasks = getTasksByBoardId(boardId, loggedInUserId);
        var taskStatuses = taskRepository.findTaskStatusesByBoardId(boardId, loggedInUserId);

        for (var taskStatus : taskStatuses) {
            var tasksStream = tasks.stream().filter((taskDto -> Objects.equals(taskDto.getTaskStatusId(), taskStatus.getId())));
            taskStatus.tasks = tasksStream.collect(Collectors.toList());
        }

        return taskStatuses;
    }

    public List<BoardDto> getAllBoards(Long loggedInUserId) {
        return taskRepository.findBoards(loggedInUserId);
    }

    public BoardDto getBoardDetail(Long boardId, Long loggedInUserId) {
        var board = boardRepository.findBoardDetail(boardId, loggedInUserId);
        board.taskStatuses = getTaskStatusesByBoard(boardId, loggedInUserId);
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
        newTask.setImage((String) payload.get("image"));
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

            var doesPermissionExist = userPermissionBoardRepository.existsByUserIdAndBoardId(Long.valueOf(assigneeId), Long.valueOf(boardId));

            if (!doesPermissionExist) {
                var userPermissionBoardEntity = new UserPermissionBoardEntity();
                userPermissionBoardEntity.setUserId(Long.valueOf(assigneeId));
                userPermissionBoardEntity.setBoardId(Long.valueOf(boardId));
                userPermissionBoardEntity.setCanView(true);
                userPermissionBoardEntity.setCanModify(true);
                userPermissionBoardEntity.setCanDelete(true);

                userPermissionBoardRepository.save(userPermissionBoardEntity);
            }
        }

        return taskRepository.findById(newTask.getId());
    }

    public Optional<TaskEntity> updateTaskStatus(Long taskId, Long taskStatusId) {
        var task = taskRepository.findById(taskId).get();
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
