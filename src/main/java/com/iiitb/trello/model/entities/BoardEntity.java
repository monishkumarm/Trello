package com.iiitb.trello.model.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "Board", schema = "Trello")
public class BoardEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id")
    private long id;
    @Basic
    @Column(name = "Name")
    private String name;
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
    @JoinColumn(name = "CreatedBy", referencedColumnName = "Id", insertable = false, updatable = false)
    private UserEntity userByCreatedBy;
    @ManyToOne
    @JoinColumn(name = "LastChangeBy", referencedColumnName = "Id", insertable = false, updatable = false)
    private UserEntity userByLastChangeBy;
    @OneToMany(mappedBy = "boardByBoardId")
    private Collection<TaskStatusEntity> taskStatusesById;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
        BoardEntity that = (BoardEntity) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(isActive, that.isActive) && Objects.equals(createdOn, that.createdOn) && Objects.equals(createdBy, that.createdBy) && Objects.equals(lastChangeOn, that.lastChangeOn) && Objects.equals(lastChangeBy, that.lastChangeBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, isActive, createdOn, createdBy, lastChangeOn, lastChangeBy);
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

    public Collection<TaskStatusEntity> getTaskStatusesById() {
        return taskStatusesById;
    }

    public void setTaskStatusesById(Collection<TaskStatusEntity> taskStatusesById) {
        this.taskStatusesById = taskStatusesById;
    }
}
