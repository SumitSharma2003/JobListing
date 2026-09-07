package com.learning.JobListing.service;

import com.learning.JobListing.model.Job;
import com.learning.JobListing.repo.JobRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class JobService {
    @Autowired
    JobRepo repo;
    public Job createJob(Job j){
        return repo.save(j);

    }
    public List<Job> getAllJobs(){
        return repo.findAll();

    }
    public List<Job> searchJobs(String keyword){
        keyword=keyword.toLowerCase();
        List<Job>ans=new ArrayList<>();
        List<Job> jobs=repo.findAll();
        for(Job j:jobs){
            String title=j.getTitle().toLowerCase();
            String desc=j.getDescp().toLowerCase();
            int exp=j.getExp();
            String skills[]=j.getSkills();
            boolean match=false;
            Integer exp2=null;
            try{
                exp2=Integer.parseInt(keyword);
            }
            catch(Exception e){

            }
            for(String skill:skills){
                if(skill.toLowerCase().contains(keyword))match=true;

            }

            if(title.contains(keyword)||desc.contains(keyword)||(exp2!=null&&exp==exp2)||match)
                ans.add(j);


        }
        return ans;
    }
}
