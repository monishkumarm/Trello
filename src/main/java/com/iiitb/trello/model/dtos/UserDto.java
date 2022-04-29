package com.iiitb.trello.model.dtos;

import java.io.Serializable;
import java.sql.Timestamp;

public class UserDto implements Serializable {
    private final Long id;
    private final String fullName;
    private final String email;
    private final Boolean isActive;
    private final Timestamp createdOn;

    public UserDto(Long id, String fullName, String email, Boolean isActive, Timestamp createdOn) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.isActive = isActive;
        this.createdOn = createdOn;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "fullName = " + fullName + ", " +
                "email = " + email + ", " +
                "isActive = " + isActive + ", " +
                "createdOn = " + createdOn + ")";
    }
}
