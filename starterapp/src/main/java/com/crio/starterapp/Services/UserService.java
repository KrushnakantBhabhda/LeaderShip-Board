package com.crio.starterapp.Services;

import java.util.List;
import java.util.Optional;

import com.crio.starterapp.Entity.User;

public interface UserService {

    User createUser(String userId,String userName);

    List<User> getAllUserBasedonScore();

    Optional<User> getSpecificUserById(String userId);

    User updateScoreOfUser(String userId,int score);

    void deleteUserById(String userId) throws Exception;
    
    
}
