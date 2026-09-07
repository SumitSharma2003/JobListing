package com.learning.JobListing.service;

import com.learning.JobListing.model.Users;
import com.learning.JobListing.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepo repo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user=repo.findUserByUsername(username);
        if(user==null){
            System.out.println("user is not found");
            //throw new UsernameNotFoundException("no user");
        }
        UserDetails userDetails=User
                .withUsername(user.getName())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
        return userDetails;

    }
}

