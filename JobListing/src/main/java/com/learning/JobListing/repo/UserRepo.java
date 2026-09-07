package com.learning.JobListing.repo;

import com.learning.JobListing.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Users,Integer> {
    @Query("select u from Users u where u.name=:username")
    Users findUserByUsername(String username);
}
