package com.DB.DBConnect.service;

import com.DB.DBConnect.controller.UserController;
import com.DB.DBConnect.entity.JournalEntry;
import com.DB.DBConnect.entity.User;
import com.DB.DBConnect.repository.JournalEntryRepository;
import com.DB.DBConnect.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void savaEntry(User user){
        userRepository.save(user);
    }

    public List<User> getAll(){
         return userRepository.findAll();
    }

    public Optional<User> findById(ObjectId id){
        return userRepository.findById(id);
    }

    public void deleteById(ObjectId id){
        userRepository.deleteById(id);
    }

    public User findByUserName(String userName){return userRepository.findByUserName(userName);}

}
