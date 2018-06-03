package com.example.springtest.Services;

import com.example.springtest.model.User;

import java.util.List;

public interface UserService {
    List<User> logIn(String username, String password);
    String registerUser(String userName, String password, String phoneNumber, String email);
    String getUserInfo(String userId);
}
