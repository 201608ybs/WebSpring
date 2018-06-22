package com.example.springtest.dao;

import com.example.springtest.model.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ProfileRepository extends MongoRepository<Profile, String> {

    Profile findByUserId(String userId);
}
