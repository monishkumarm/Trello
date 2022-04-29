package com.iiitb.trello.model.dtos;

import java.io.Serializable;
import java.util.List;

public class BoardDto implements Serializable {
    private final Long id;
    private final String name;

    public List<TaskStatusDto> taskStatuses;

    public BoardDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<TaskStatusDto> getTaskStatuses(){
        return taskStatuses;
    }
}
