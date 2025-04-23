package com.DB.DBConnect.controller;

import com.DB.DBConnect.entity.User;
import com.DB.DBConnect.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //get all user
    @GetMapping
    public List<User> gettAllUsers(){
          return userService.getAll();
    }

    //Create a User
    @PostMapping
    public void createUser(@RequestBody User user){
           userService.savaEntry(user);
    }

    //update in User Entry
    @PutMapping("/{userName}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String userName){
        User userInDb = userService.findByUserName(userName);
        if(userInDb != null){
              userInDb.setUserName(user.getUserName());
              userInDb.setPassword(user.getPassword());
              userService.savaEntry(userInDb);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
