package com.example.springtest.Services;

public interface ProfileService {

    String getProfile(String userId);

    void insertProfile(String userId, String profile);

    void deleteProfile(String userId);
}
