package com.iiitb.trello;

import com.iiitb.trello.model.User;
import com.iiitb.trello.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Random;

@SpringBootApplication
public class TrelloApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    Random random = new Random();

    public void CreateNewUser(){
        var newId = (long) random.nextInt(100);
        User user = new User();
        user.setId(newId);
        user.setUsername("user"+newId);
        user.setRole("admin");
        user.setEmail("user"+newId+"@gmail.com");
        user.setPassword("password");
        user.setEnabled(true);

        User savedUser = userRepository.save(user);

        System.out.println(savedUser);
    }

    public static void main(String[] args) {

        SpringApplication.run(TrelloApplication.class, args);

    }

    //temporary, to be removed
    @Override
    public void run(String... args) {

        CreateNewUser();
    }
}
