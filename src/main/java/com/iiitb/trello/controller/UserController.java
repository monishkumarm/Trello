package com.iiitb.trello.controller;

import com.iiitb.trello.model.entities.UserEntity;
import com.iiitb.trello.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/sign-up")
    public void registerNewUser(@RequestBody UserEntity userEntity){
         userService.addNewUser(userEntity);
    }
}
