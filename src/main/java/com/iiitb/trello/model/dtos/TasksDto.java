package com.iiitb.trello.model.dtos;

import com.iiitb.trello.model.entities.TaskEntity;

import java.util.List;

public class TasksDto {
    public List<TaskEntity> tasks;

    public TasksDto(List<TaskEntity> tasks){
        this.tasks = tasks;
    }
}
