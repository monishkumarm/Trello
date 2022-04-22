package com.iiitb.trello.model.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "User", schema = "Trello")
public class UserEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id")
    private Long id;
    @Basic
    @Column(name = "FullName")
    private String fullName;
    @Basic
    @Column(name = "Email")
    private String email;
    @Basic
    @Column(name = "TimeZoneId")
    private Integer timeZoneId;
    @Basic
    @Column(name = "Password")
    private String password;
    @Basic
    @Column(name = "IsActive")
    private Boolean isActive;
    @Basic
    @Column(name = "CreatedOn")
    private Timestamp createdOn;
    @OneToMany(mappedBy = "userByCreatedBy")
    private Collection<BoardEntity> boardsById;
    @OneToMany(mappedBy = "userByLastChangeBy")
    private Collection<BoardEntity> boardsById_0;
    @OneToMany(mappedBy = "userByCreatedBy")
    private Collection<TaskEntity> tasksById;
    @OneToMany(mappedBy = "userByLastChangeBy")
    private Collection<TaskEntity> tasksById_0;
    @OneToMany(mappedBy = "userByUserId")
    private Collection<TaskAssigneeEntity> taskAssigneesById;
    @OneToMany(mappedBy = "userByUserId")
    private Collection<TaskCommentEntity> taskCommentsById;
    @OneToMany(mappedBy = "userByUserId")
    private Collection<UserPermissionBoardEntity> userPermissionBoardsById;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getTimeZoneId() {
        return timeZoneId;
    }

    public void setTimeZoneId(Integer timeZoneId) {
        this.timeZoneId = timeZoneId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(fullName, that.fullName) && Objects.equals(email, that.email) && Objects.equals(timeZoneId, that.timeZoneId) && Objects.equals(password, that.password) && Objects.equals(isActive, that.isActive) && Objects.equals(createdOn, that.createdOn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, email, timeZoneId, password, isActive, createdOn);
    }

    public Collection<BoardEntity> getBoardsById() {
        return boardsById;
    }

    public void setBoardsById(Collection<BoardEntity> boardsById) {
        this.boardsById = boardsById;
    }

    public Collection<BoardEntity> getBoardsById_0() {
        return boardsById_0;
    }

    public void setBoardsById_0(Collection<BoardEntity> boardsById_0) {
        this.boardsById_0 = boardsById_0;
    }

    public Collection<TaskEntity> getTasksById() {
        return tasksById;
    }

    public void setTasksById(Collection<TaskEntity> tasksById) {
        this.tasksById = tasksById;
    }

    public Collection<TaskEntity> getTasksById_0() {
        return tasksById_0;
    }

    public void setTasksById_0(Collection<TaskEntity> tasksById_0) {
        this.tasksById_0 = tasksById_0;
    }

    public Collection<TaskAssigneeEntity> getTaskAssigneesById() {
        return taskAssigneesById;
    }

    public void setTaskAssigneesById(Collection<TaskAssigneeEntity> taskAssigneesById) {
        this.taskAssigneesById = taskAssigneesById;
    }

    public Collection<TaskCommentEntity> getTaskCommentsById() {
        return taskCommentsById;
    }

    public void setTaskCommentsById(Collection<TaskCommentEntity> taskCommentsById) {
        this.taskCommentsById = taskCommentsById;
    }

    public Collection<UserPermissionBoardEntity> getUserPermissionBoardsById() {
        return userPermissionBoardsById;
    }

    public void setUserPermissionBoardsById(Collection<UserPermissionBoardEntity> userPermissionBoardsById) {
        this.userPermissionBoardsById = userPermissionBoardsById;
    }
}
