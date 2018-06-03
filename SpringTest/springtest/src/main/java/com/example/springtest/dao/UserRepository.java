package com.example.springtest.dao;

import com.example.springtest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
//    void save(User user);
    List<User> findByUsername(String userName);
    List<User> findByUsernameAndPassword(String username, String password);
    List<User> findByUserId(String userId);
}
