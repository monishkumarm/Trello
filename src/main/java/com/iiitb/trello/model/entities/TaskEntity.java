package com.iiitb.trello.model.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "Task", schema = "Trello")
public class TaskEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id")
    private Long id;
    @Basic
    @Column(name = "BoardId")
    private Long boardId;
    @Basic
    @Column(name = "Name")
    private String name;
    @Basic
    @Column(name = "Description")
    private String description;
    @Basic
    @Column(name = "TaskStatusId")
    private Long taskStatusId;
    @Basic
    @Column(name = "IsActive")
    private Boolean isActive;
    @Basic
    @Column(name = "CreatedOn")
    private Timestamp createdOn;
    @Basic
    @Column(name = "CreatedBy")
    private Long createdBy;
    @Basic
    @Column(name = "LastChangeOn")
    private Timestamp lastChangeOn;
    @Basic
    @Column(name = "LastChangeBy")
    private Long lastChangeBy;
    @ManyToOne
    @JoinColumn(name = "BoardId", referencedColumnName = "Id", insertable = false, updatable = false, nullable = false)
    private BoardEntity boardByBoardId;
    @ManyToOne
    @JoinColumn(name = "TaskStatusId", referencedColumnName = "Id", insertable = false, updatable = false, nullable = false)
    private TaskStatusEntity taskStatusByTaskStatusId;
    @ManyToOne
    @JoinColumn(name = "CreatedBy", referencedColumnName = "Id", insertable = false, updatable = false, nullable = false)
    private UserEntity userByCreatedBy;
    @ManyToOne
    @JoinColumn(name = "LastChangeBy", referencedColumnName = "Id", insertable = false, updatable = false, nullable = false)
    private UserEntity userByLastChangeBy;
    @OneToMany(mappedBy = "taskByTaskId")
    private Collection<TaskAssigneeEntity> taskAssigneesById;
    @OneToMany(mappedBy = "taskByTaskId")
    private Collection<TaskCommentEntity> taskCommentsById;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBoardId() {
        return boardId;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getTaskStatusId() {
        return taskStatusId;
    }

    public void setTaskStatusId(Long taskStatusId) {
        this.taskStatusId = taskStatusId;
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

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getLastChangeOn() {
        return lastChangeOn;
    }

    public void setLastChangeOn(Timestamp lastChangeOn) {
        this.lastChangeOn = lastChangeOn;
    }

    public Long getLastChangeBy() {
        return lastChangeBy;
    }

    public void setLastChangeBy(Long lastChangeBy) {
        this.lastChangeBy = lastChangeBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskEntity that = (TaskEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(boardId, that.boardId) && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(taskStatusId, that.taskStatusId) && Objects.equals(isActive, that.isActive) && Objects.equals(createdOn, that.createdOn) && Objects.equals(createdBy, that.createdBy) && Objects.equals(lastChangeOn, that.lastChangeOn) && Objects.equals(lastChangeBy, that.lastChangeBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, boardId, name, description, taskStatusId, isActive, createdOn, createdBy, lastChangeOn, lastChangeBy);
    }

    public BoardEntity getBoardByBoardId() {
        return boardByBoardId;
    }

    public void setBoardByBoardId(BoardEntity boardByBoardId) {
        this.boardByBoardId = boardByBoardId;
    }

    public TaskStatusEntity getTaskStatusByTaskStatusId() {
        return taskStatusByTaskStatusId;
    }

    public void setTaskStatusByTaskStatusId(TaskStatusEntity taskStatusByTaskStatusId) {
        this.taskStatusByTaskStatusId = taskStatusByTaskStatusId;
    }

    public UserEntity getUserByCreatedBy() {
        return userByCreatedBy;
    }

    public void setUserByCreatedBy(UserEntity userByCreatedBy) {
        this.userByCreatedBy = userByCreatedBy;
    }

    public UserEntity getUserByLastChangeBy() {
        return userByLastChangeBy;
    }

    public void setUserByLastChangeBy(UserEntity userByLastChangeBy) {
        this.userByLastChangeBy = userByLastChangeBy;
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
}
