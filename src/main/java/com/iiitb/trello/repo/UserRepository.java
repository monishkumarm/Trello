package com.iiitb.trello.repo;

import com.iiitb.trello.model.entities.UserEntity;
import com.iiitb.trello.model.dtos.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByEmail(String email);

    @Query(value = "SELECT " +
            "new com.iiitb.trello.model.dtos.UserDto(" +
            "t.id, " +
            "t.fullName, " +
            "t.email, " +
            "t.isActive) " +
            "FROM " +
            "UserEntity AS t")
    List<UserDto> findAllUsers();
}
