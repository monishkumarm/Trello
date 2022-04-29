package com.iiitb.trello.services;

import com.iiitb.trello.model.dtos.BoardDto;
import com.iiitb.trello.model.dtos.TaskDto;
import com.iiitb.trello.model.dtos.TaskStatusDto;
import com.iiitb.trello.model.entities.TaskEntity;
import com.iiitb.trello.repo.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<TaskDto> getTasks() {
        return taskRepository.findTasks();
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

    public List<BoardDto> getBoards(){
        var taskStatuses = getTaskStatuses();
        var boards = taskRepository.findBoards();

        for(var board : boards){
            var taskStatusesStream = taskStatuses.stream().filter((taskStatusDto -> Objects.equals(taskStatusDto.getBoardId(), board.getId())));
            board.taskStatuses = taskStatusesStream.collect(Collectors.toList());
        }

        return boards;
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
