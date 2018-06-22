package com.example.springtest.Controller;

import com.example.springtest.Services.ProfileService;
import com.example.springtest.Util.AppConfig;
import com.example.springtest.model.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    ProfileService profileService;

    @GetMapping("/get")
    @ResponseBody
    public String getProfile(){
        String userId = AppConfig.getUserId();
        return profileService.getProfile(userId);
    }

    @PostMapping("/insert")
    public void insertProfile(@Param("base64Str") String base64Str){
        String userId = AppConfig.getUserId();
        profileService.insertProfile(userId,base64Str);
    }

    @PostMapping("/delete")
    public void deleteProfile(){
        String userId = AppConfig.getUserId();
        profileService.deleteProfile(userId);
    }
}
