package org.example.evotingapp.data.repository;

import org.example.evotingapp.data.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {
}
