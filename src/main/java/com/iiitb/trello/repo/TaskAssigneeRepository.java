package com.iiitb.trello.repo;

import com.iiitb.trello.model.entities.TaskAssigneeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskAssigneeRepository extends JpaRepository<TaskAssigneeEntity, Long> {
}
