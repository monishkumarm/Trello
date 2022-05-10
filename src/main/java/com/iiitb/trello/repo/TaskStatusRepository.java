package com.iiitb.trello.repo;

import com.iiitb.trello.model.entities.TaskStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskStatusRepository extends JpaRepository<TaskStatusEntity,Long>{
}
