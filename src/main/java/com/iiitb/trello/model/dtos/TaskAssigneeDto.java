package com.iiitb.trello.model.dtos;

import java.io.Serializable;

public class TaskAssigneeDto implements Serializable {
    private final Long id;
    private final Long taskId;
    private final Long userId;
    private final UserDto assignee;

    public TaskAssigneeDto(Long id, Long taskId, Long userId, UserDto assignee) {
        this.id = id;
        this.taskId = taskId;
        this.userId = userId;
        this.assignee = assignee;
    }

    public Long getId() {
        return id;
    }

    public Long getTaskId() {
        return taskId;
    }

    public Long getUserId() {
        return userId;
    }

    public UserDto getAssignee() {
        return assignee;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "taskId = " + taskId + ", " +
                "userId = " + userId + ")";
    }
}
