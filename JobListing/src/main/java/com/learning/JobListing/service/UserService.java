package com.learning.JobListing.service;

import com.learning.JobListing.model.Users;
import com.learning.JobListing.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepo repo;
    @Autowired
    private AuthenticationManager authmanager;
    @Autowired
    private JWTService service;
    public Users registerUser(Users user){
        return repo.save(user);
    }
    public List<Users> getAllUsers(){
        return repo.findAll();

    }
    public String login(Users user){
       Authentication authentication= authmanager.authenticate(new UsernamePasswordAuthenticationToken(user.getName(),user.getPassword()));
       if(authentication.isAuthenticated()) return service.generateToken(authentication.getName());
       else return "Invaid user";
    }
}

