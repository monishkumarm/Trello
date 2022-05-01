package com.iiitb.trello.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class Home {

    @RequestMapping("/welcome")
    public String welcome(){
        String text="Hi, hello!!";
        return text;
    }

    @RequestMapping("/getUsers")
    public String getUsers(){
        String text="{\"name\":\"monish\"}";
        return text;
    }
}
