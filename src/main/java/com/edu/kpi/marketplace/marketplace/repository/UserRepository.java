package com.edu.kpi.marketplace.marketplace.repository;

import com.edu.kpi.marketplace.marketplace.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserRepository extends MongoRepository<User, String> {
    User findByEmail(String email);
}
