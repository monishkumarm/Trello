package com.iiitb.trello.services;

import com.iiitb.trello.model.dtos.UserDto;
import com.iiitb.trello.repo.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeService {
    private final UserRepository userRepository;

    public HomeService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDto> getAllUsers() {
        var users = userRepository.findAllUsers();
        return users;
    }
}
