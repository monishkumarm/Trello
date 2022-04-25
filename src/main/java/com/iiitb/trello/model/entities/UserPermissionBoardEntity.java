package com.iiitb.trello.model.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "UserPermissionBoard", schema = "Trello")
public class UserPermissionBoardEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id")
    private Long id;
    @Basic
    @Column(name = "BoardId")
    private Long boardId;
    @Basic
    @Column(name = "UserId")
    private Long userId;
    @Basic
    @Column(name = "CanView")
    private Boolean canView;
    @Basic
    @Column(name = "CanModify")
    private Boolean canModify;
    @Basic
    @Column(name = "CanDelete")
    private Boolean canDelete;
    @ManyToOne
    @JoinColumn(name = "BoardId", referencedColumnName = "Id", insertable = false, updatable = false, nullable = false)
    private BoardEntity boardByBoardId;
    @ManyToOne
    @JoinColumn(name = "UserId", referencedColumnName = "Id", insertable = false, updatable = false, nullable = false)
    private UserEntity userByUserId;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Boolean getCanView() {
        return canView;
    }

    public void setCanView(Boolean canView) {
        this.canView = canView;
    }

    public Boolean getCanModify() {
        return canModify;
    }

    public void setCanModify(Boolean canModify) {
        this.canModify = canModify;
    }

    public Boolean getCanDelete() {
        return canDelete;
    }

    public void setCanDelete(Boolean canDelete) {
        this.canDelete = canDelete;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPermissionBoardEntity that = (UserPermissionBoardEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(boardId, that.boardId) && Objects.equals(userId, that.userId) && Objects.equals(canView, that.canView) && Objects.equals(canModify, that.canModify) && Objects.equals(canDelete, that.canDelete);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, boardId, userId, canView, canModify, canDelete);
    }

    public BoardEntity getBoardByBoardId() {
        return boardByBoardId;
    }

    public void setBoardByBoardId(BoardEntity boardByBoardId) {
        this.boardByBoardId = boardByBoardId;
    }

    public UserEntity getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(UserEntity userByUserId) {
        this.userByUserId = userByUserId;
    }
}
