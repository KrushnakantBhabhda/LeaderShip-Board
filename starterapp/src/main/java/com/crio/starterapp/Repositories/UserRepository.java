package com.crio.starterapp.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.crio.starterapp.Entity.User;

@Repository
public interface UserRepository extends MongoRepository<User,String>{
  

    Optional<User> findByUserId(String userId);
    void deleteByUserId(String userId);
    
}
