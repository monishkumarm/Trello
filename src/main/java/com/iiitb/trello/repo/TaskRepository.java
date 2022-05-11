package com.iiitb.trello.repo;

import com.iiitb.trello.model.dtos.BoardDto;
import com.iiitb.trello.model.dtos.TaskDto;
import com.iiitb.trello.model.dtos.TaskStatusDto;
import com.iiitb.trello.model.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

    @Query(value = "SELECT " +
            "new com.iiitb.trello.model.dtos.TaskDto(" +
            "t.id, " +
            "t.taskStatusId, " +
            "t.name, " +
            "t.description, " +
            "t.image) " +
            "FROM " +
            "TaskEntity AS t " +
            "INNER JOIN BoardEntity AS b ON t.boardId = b.id " +
            "INNER JOIN UserPermissionBoardEntity AS upb ON b.id = upb.boardId " +
            "WHERE t.boardId = ?1 " +
            "AND upb.userId = ?2 " +
            "AND upb.canView = true")
    List<TaskDto> findTasksByBoardId(Long boardId, Long loggedInUserId);

    @Query(value = "SELECT " +
            "new com.iiitb.trello.model.dtos.TaskStatusDto(" +
            "ts.id, " +
            "ts.boardId, " +
            "ts.name, " +
            "ts.isActive)" +
            "FROM " +
            "TaskStatusEntity AS ts " +
            "INNER JOIN BoardEntity AS b ON ts.boardId = b.id " +
            "INNER JOIN UserPermissionBoardEntity AS upb ON b.id = upb.boardId " +
            "WHERE ts.boardId = ?1 " +
            "AND upb.userId = ?2 " +
            "AND upb.canView = true")
    List<TaskStatusDto> findTaskStatusesByBoardId(Long boardId, Long loggedInUserId);

    @Query(value = "SELECT " +
            "new com.iiitb.trello.model.dtos.BoardDto(" +
            "b.id, " +
            "b.name) " +
            "FROM " +
            "BoardEntity AS b " +
            "INNER JOIN UserPermissionBoardEntity AS upb ON b.id = upb.boardId " +
            "WHERE " +
            "upb.userId = ?1 " +
            "AND upb.canView = true ")
    List<BoardDto> findBoards(Long loggedInUserId);

}