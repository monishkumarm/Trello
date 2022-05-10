package com.iiitb.trello.controller;

import com.iiitb.trello.model.dtos.UserDto;
import com.iiitb.trello.model.entities.UserEntity;
import com.iiitb.trello.services.HomeService;
import com.iiitb.trello.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class Home {

    private final HomeService homeService;

    @Autowired
    public Home(HomeService homeService) {
        this.homeService = homeService;
    }
    @RequestMapping("/welcome")
    public String welcome(){
        String text="Dobro Jutro";
        return text;
    }

    @GetMapping("/getUsers")
    public List<UserDto> getUsers(){
        var users=homeService.getAllUsers();
        return users;
    }
}
