package com.iiitb.trello.model.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "TaskStatus", schema = "Trello")
public class TaskStatusEntity {
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
    @Column(name = "IsActive")
    private Boolean isActive;
    @OneToMany(mappedBy = "taskStatusByTaskStatusId")
    private Collection<TaskEntity> tasksById;
    @ManyToOne
    @JoinColumn(name = "BoardId", referencedColumnName = "Id", insertable = false, updatable = false, nullable = false)
    private BoardEntity boardByBoardId;


    public TaskStatusEntity()
    {
    }


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

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskStatusEntity that = (TaskStatusEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(boardId, that.boardId) && Objects.equals(name, that.name) && Objects.equals(isActive, that.isActive);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, boardId, name, isActive);
    }

    public Collection<TaskEntity> getTasksById() {
        return tasksById;
    }

    public void setTasksById(Collection<TaskEntity> tasksById) {
        this.tasksById = tasksById;
    }

    public BoardEntity getBoardByBoardId() {
        return boardByBoardId;
    }

    public void setBoardByBoardId(BoardEntity boardByBoardId) {
        this.boardByBoardId = boardByBoardId;
    }
}
