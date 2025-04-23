package com.DB.DBConnect.repository;

import com.DB.DBConnect.entity.JournalEntry;
import com.DB.DBConnect.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {
        User findByUserName(String username);
}
