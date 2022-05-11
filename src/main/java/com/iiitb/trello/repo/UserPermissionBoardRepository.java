package com.iiitb.trello.repo;

import com.iiitb.trello.model.entities.UserPermissionBoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPermissionBoardRepository extends JpaRepository<UserPermissionBoardEntity, Long> {

    Boolean existsByUserIdAndBoardId(Long userId, Long boardId);

}
