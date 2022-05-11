package com.iiitb.trello.repo;

import com.iiitb.trello.model.dtos.BoardDto;
import com.iiitb.trello.model.entities.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

    @Query(value = "SELECT " +
            "new com.iiitb.trello.model.dtos.BoardDto(" +
            "b.id, " +
            "b.name) " +
            "FROM " +
            "BoardEntity AS b " +
            "INNER JOIN UserPermissionBoardEntity AS upb ON b.id = upb.boardId "+
            "WHERE "+
            "b.id=?1 " +
            "AND upb.userId = ?2 " +
            "AND upb.canView = true ")
    BoardDto findBoardDetail(Long boardId, Long loggedInUserId);
}