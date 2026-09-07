package com.learning.JobListing.repo;

import com.learning.JobListing.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepo extends JpaRepository<Job,Integer> {
}
