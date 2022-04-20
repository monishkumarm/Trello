package com.iiitb.trello.repo;

import com.iiitb.trello.model.entities.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
}