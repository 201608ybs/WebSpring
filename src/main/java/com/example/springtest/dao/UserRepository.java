package com.example.springtest.dao;

import com.example.springtest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, String> {
//    void save(User user);
    List<User> findByUsername(String userName);
    List<User> findByUsernameAndPassword(String username, String password);
    List<User> findByUserId(String userId);
    List<User> findByRole(String role);
}
