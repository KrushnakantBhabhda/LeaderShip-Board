package com.crio.starterapp.Services;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.swing.text.html.Option;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Exceptions.UserNotFoundException;
import com.crio.starterapp.Entity.User;
import com.crio.starterapp.Repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    public UserRepository userRepository;

    @Override
    public User createUser(String userId, String userName) {
        // TODO Auto-generated method stub
       User user=new User(userId,userName,0,new HashSet<>());
       return userRepository.save(user);
    }

    @Override
    public List<User> getAllUserBasedonScore() {
        List<User> users = userRepository.findAll();
        
        // Sort users based on score in descending order using Comparator
        users.sort(Comparator.comparingInt(User::getScore).reversed());
        
        return users;
    }

    @Override
    public Optional<User> getSpecificUserById(String userId) {
        Optional<User> u= userRepository.findByUserId(userId);

        if(u.isPresent()){
            return u;
        }
        else{
            throw  new UserNotFoundException("User does not exist");
        }

    }

    @Override
    public User updateScoreOfUser(String userId, int score) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    
        // Validate score range
        if (score < 0 || score > 100) {
            throw new IllegalArgumentException("Score must be between 0 and 100");
        }
    
        // Update score and badges
        user.setScore(score);
        HashSet<String> badges=new HashSet<>();
        if(user.getBadges().size()!=0){
             badges = user.getBadges();

        }
    
        
        if (score >= 1 && score < 30) {
            badges.add("Code_Ninja");
        } else if (score >= 30 && score < 60) {
            badges.add("Code_Champ");
        } else if (score >= 60 && score <= 100) {
            badges.add("Code_Master");
        }
        user.setBadges(badges);
    
        return userRepository.save(user);
    }
    

    @Override
    public void deleteUserById(String userId) {
        Optional<User> u = userRepository.findByUserId(userId);
        if (u.isPresent()) {
            userRepository.deleteByUserId(u.get().getUserId());
        } else {
            throw new UserNotFoundException("User does not exist");
        }
    }

    
}
