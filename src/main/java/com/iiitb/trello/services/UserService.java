package com.iiitb.trello.services;

import com.iiitb.trello.model.entities.UserEntity;
import com.iiitb.trello.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<UserEntity> addNewUser(UserEntity userEntity)
    {
        userRepository.save(userEntity);
        return userRepository.findById(userEntity.getId());
    }
}
