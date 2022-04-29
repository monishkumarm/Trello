package com.iiitb.trello.controller;

import com.iiitb.trello.model.entities.UserEntity;
import com.iiitb.trello.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/sign-up", method = RequestMethod.POST)
    public void registerNewUser(@RequestBody UserEntity userEntity){
         userService.addNewUser(userEntity);
    }
}
