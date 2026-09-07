package com.learning.JobListing.controller;

import com.learning.JobListing.model.Users;
import com.learning.JobListing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService service;
    @PostMapping("/register")
    public Users registerUser(@RequestBody Users user){
        System.out.println("user is registering");
        return service.registerUser(user);


    }
    @GetMapping("/users")
    public List<Users> getAllUsers(){
        System.out.println("I am getting users");
        return service.getAllUsers();
    }
    @PostMapping("/loginUser")
    public String login(@RequestBody Users user){
        return service.login(user);
    }

}
