package com.crio.starterapp.Controller;

import java.io.Console;
import java.util.List;

import org.apache.el.stream.Optional;
// import org.hibernate.validator.internal.util.logging.Log_.logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crio.starterapp.Dto.ScoreDto;
import com.crio.starterapp.Dto.UserDto;
import com.crio.starterapp.Entity.User;
import com.crio.starterapp.Services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class Controller {
    @Autowired
    public  UserService userService;
    
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUserBasedonScore();
        if (users.isEmpty()) {
            System.out.println("No users found");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        System.out.println("Users found: " + users.size());
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    


    //Getting the particular user based on the userId
    @GetMapping("/{userId}")
public ResponseEntity<User> getUserById(@PathVariable("userId") String userId) {
    // Console.Log("recieved request to view");
    java.util.Optional<User> userOptional = userService.getSpecificUserById(userId);
    if (userOptional.isPresent()) {
        return ResponseEntity.ok(userOptional.get());
    } else {
        return ResponseEntity.notFound().build();
    }
}
    @PostMapping()
    public ResponseEntity<Object> createUser(@RequestBody UserDto userDto) {
        try {
            User createdUser = userService.createUser(userDto.getUserId(), userDto.getUserName());
            return ResponseEntity.ok(createdUser);
        } catch (Exception e) {
            // Log the exception
            e.printStackTrace();
            // Return a meaningful error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUserScore(@PathVariable("userId") String userId, @RequestBody ScoreDto score) {
        try {
            if (score.getScore() < 0 || score.getScore() > 100) {
                throw new IllegalArgumentException("Score must be between 0 and 100");
            }
            User updatedUser = userService.updateScoreOfUser(userId, score.getScore());
            return ResponseEntity.ok(updatedUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null); // Return bad request for invalid score
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    


    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUserById(@PathVariable("userId") String userId) throws Exception {
        userService.deleteUserById(userId);
        return ResponseEntity.noContent().build();
    }






    

}
