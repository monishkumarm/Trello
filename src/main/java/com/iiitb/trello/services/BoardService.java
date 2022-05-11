package com.iiitb.trello.services;

import com.iiitb.trello.model.CustomUserDetails;
import com.iiitb.trello.model.entities.BoardEntity;
import com.iiitb.trello.model.entities.TaskStatusEntity;
import com.iiitb.trello.model.entities.UserPermissionBoardEntity;
import com.iiitb.trello.repo.BoardRepository;
import com.iiitb.trello.repo.TaskStatusRepository;
import com.iiitb.trello.repo.UserPermissionBoardRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final TaskStatusRepository taskStatusRepository;

    private final UserPermissionBoardRepository userPermissionBoardRepository;

    public BoardService(BoardRepository BoardRepository, TaskStatusRepository taskStatusRepository, UserPermissionBoardRepository userPermissionBoardRepository) {
        this.boardRepository = BoardRepository;
        this.taskStatusRepository = taskStatusRepository;
        this.userPermissionBoardRepository = userPermissionBoardRepository;
    }

    public Optional<BoardEntity> createBoard(BoardEntity newBoard) {
        //Date date=new Date();
        newBoard.setCreatedBy(getLoggedInUserId());
        newBoard.setActive(true);
        newBoard.setCreatedOn(Timestamp.from(Instant.now()));
        newBoard.setLastChangeBy(getLoggedInUserId());
        newBoard.setLastChangeOn(Timestamp.from(Instant.now()));
        boardRepository.save(newBoard);
        //create task status for newBoard

        TaskStatusEntity taskStatus = new TaskStatusEntity();
        taskStatus.setActive(true);
        taskStatus.setBoardId(newBoard.getId());
        taskStatus.setName("To Do");
        taskStatusRepository.save(taskStatus);

        TaskStatusEntity taskStatus2 = new TaskStatusEntity();
        taskStatus2.setActive(true);
        taskStatus2.setBoardId(newBoard.getId());
        taskStatus2.setName("Doing");
        taskStatusRepository.save(taskStatus2);

        TaskStatusEntity taskStatus3 = new TaskStatusEntity();
        taskStatus3.setActive(true);
        taskStatus3.setBoardId(newBoard.getId());
        taskStatus3.setName("Done");
        taskStatusRepository.save(taskStatus3);


        TaskStatusEntity taskStatus4 = new TaskStatusEntity();
        taskStatus4.setActive(true);
        taskStatus4.setBoardId(newBoard.getId());
        taskStatus4.setName("Rejected");
        taskStatusRepository.save(taskStatus4);

        var userPermissionBoardEntity = new UserPermissionBoardEntity();
        userPermissionBoardEntity.setUserId(newBoard.getCreatedBy());
        userPermissionBoardEntity.setBoardId(newBoard.getId());
        userPermissionBoardEntity.setCanView(true);
        userPermissionBoardEntity.setCanModify(true);
        userPermissionBoardEntity.setCanDelete(true);

        userPermissionBoardRepository.save(userPermissionBoardEntity);

        return boardRepository.findById(newBoard.getId());
    }

    private Long getLoggedInUserId() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        var loggedInUser = (CustomUserDetails) auth.getPrincipal();
        return loggedInUser.getUserId();
    }
}
