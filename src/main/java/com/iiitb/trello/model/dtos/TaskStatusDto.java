package com.iiitb.trello.model.dtos;

import java.io.Serializable;
import java.util.List;

public class TaskStatusDto implements Serializable {
    private final Long id;
    private final Long boardId;
    private final String name;
    private final Boolean isActive;

    public List<TaskDto> tasks;

    public TaskStatusDto(Long id, Long boardId, String name, Boolean isActive) {
        this.id = id;
        this.boardId = boardId;
        this.name = name;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public Long getBoardId() {
        return boardId;
    }

    public String getName() {
        return name;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "boardId = " + boardId + ", " +
                "name = " + name + ", " +
                "isActive = " + isActive + ")";
    }

    public List<TaskDto> getTasks() {
        return tasks;
    }
}
