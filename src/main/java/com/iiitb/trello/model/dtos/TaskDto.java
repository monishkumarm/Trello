package com.iiitb.trello.model.dtos;

import java.io.Serializable;

public class TaskDto implements Serializable {
    private final Long id;
    private final Long taskStatusId;
    private final String name;
    private final String description;
    private final String image;

    public TaskDto(Long id, Long taskStatusId, String name, String description, String image){
        this.id = id;
        this.taskStatusId = taskStatusId;
        this.name = name;
        this.description = description;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }


    public Long getTaskStatusId() {
        return taskStatusId;
    }

    public String getImage() {
        return image;
    }
}
