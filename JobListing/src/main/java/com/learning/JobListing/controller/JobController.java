package com.learning.JobListing.controller;

import com.learning.JobListing.model.Job;
import com.learning.JobListing.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class JobController {
    @Autowired
    JobService service;
    @GetMapping("/")
    public String greet(){
        return "Hello";
    }
    @GetMapping("/jobs")
    public List<Job> getAllJobs(){
        System.out.println("I am  getting jobs");
        return service.getAllJobs();
    }

    @PostMapping("/createJob")
    public Job createJob(@RequestBody Job j){
        return service.createJob(j);

    }
    @GetMapping("/search")
    public List<Job> searchJobs(@RequestParam String keyword){
        return service.searchJobs(keyword);

    }
}
