package com.example.springtest.Services.serviceImpl;

import com.example.springtest.JavaBean.UserProfile;
import com.example.springtest.Services.ProfileService;
import com.example.springtest.dao.ProfileRepository;
import com.example.springtest.model.Profile;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.Null;

import java.util.ArrayList;
import java.util.List;

import static org.apache.logging.log4j.ThreadContext.isEmpty;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Resource
    private ProfileRepository profileRepository;

    @Override
    public String getProfile(String userID){
        Gson gson = new Gson();
        List<UserProfile> profiles = new ArrayList<UserProfile>();
        Profile profile = profileRepository.findByUserId(userID);
        if (profile != null){
            UserProfile userProfile = new UserProfile();
            userProfile.setName("ret.jpg");
            userProfile.setStatus("done");
            userProfile.setThumbUrl(profile.getProfile());
            userProfile.setUid(0);
            profiles.add(userProfile);
        }
        String resStr = new GsonBuilder().setDateFormat("yyyy-MM-dd").create().toJson(profiles);
        return resStr;
    }

    @Override
    public void insertProfile(String userId, String profile){
        Profile newProfile = new Profile();
        newProfile.setUserId(userId);
        newProfile.setProfile(profile);
        profileRepository.save(newProfile);
    }

    @Override
    public void deleteProfile(String userId){
        Profile profile = profileRepository.findByUserId(userId);
        if (profile != null){
            profileRepository.delete(profile);
        }
    }

}
