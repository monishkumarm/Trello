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
            "t.description) " +
            "FROM " +
            "TaskEntity AS t")
    List<TaskDto> findTasks();

    @Query(value = "SELECT " +
            "new com.iiitb.trello.model.dtos.TaskDto(" +
            "t.id, " +
            "t.taskStatusId, " +
            "t.name, " +
            "t.description) " +
            "FROM " +
            "TaskEntity AS t " +
            "WHERE t.boardId = ?1")
    List<TaskDto> findTasksByBoardId(Long boardId);

    @Query(value = "SELECT " +
            "new com.iiitb.trello.model.dtos.TaskStatusDto(" +
            "ts.id, " +
            "ts.boardId, " +
            "ts.name, " +
            "ts.isActive)" +
            "FROM " +
            "TaskStatusEntity AS ts")
    List<TaskStatusDto> findTaskStatuses();


    @Query(value = "SELECT " +
            "new com.iiitb.trello.model.dtos.TaskStatusDto(" +
            "ts.id, " +
            "ts.boardId, " +
            "ts.name, " +
            "ts.isActive)" +
            "FROM " +
            "TaskStatusEntity AS ts " +
            "WHERE ts.boardId = ?1")
    List<TaskStatusDto> findTaskStatusesByBoardId(Long boardId);

    @Query(value = "SELECT " +
            "new com.iiitb.trello.model.dtos.BoardDto(" +
            "b.id, " +
            "b.name) " +
            "FROM " +
            "BoardEntity AS b ")
    List<BoardDto> findBoards();

}