package com.learning.JobListing.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Job {
    static{
        System.out.println("I am in Job static block");

    }
    @Id
    private int id;
    private String title;
    private String descp;
    private int exp;
    private String [] skills;
}
