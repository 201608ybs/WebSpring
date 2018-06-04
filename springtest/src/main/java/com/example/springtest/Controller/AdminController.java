package com.example.springtest.Controller;

import com.example.springtest.Services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/administrator")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping(value = "getAdminProblem")
    @ResponseBody
    public String getAdminProblem(){
        return adminService.getAdminProblem();
    }

    @PostMapping(value = "getProblemByTag")
    @ResponseBody
    public String getProblemByTag(@RequestParam("tag") String tagContent){
        return adminService.getProblemByTag(tagContent);
    }

    @PostMapping(value = "getProblemById")
    @ResponseBody
    public String getProblemById(@RequestParam("problemId") String problemId){
        return adminService.getProblemById(problemId);
    }

    @PostMapping(value = "getProblemByUser")
    @ResponseBody
    public String getProblemByUser(@RequestParam("userName") String userName){
        return adminService.getProblemByUser(userName);
    }

    @GetMapping(value = "getAllProblem")
    @ResponseBody
    public String getAllProblem(){
        return adminService.getAllProblem();
    }

    @GetMapping(value = "getUser")
    @ResponseBody
    public String getAllUser(){
        return adminService.getAllUser();
    }

    @PostMapping(value = "updateUserInfo")
    @ResponseBody
    public String updateUserInfo(@RequestParam("userId") String userId,
                                 @RequestParam("userName") String username,
                                 @RequestParam("password") String password,
                                 @RequestParam("phoneNumber") String phone,
                                 @RequestParam("email") String email){
        return adminService.updateUserInfo(userId, username, password, phone, email);
    }

    @PostMapping(value = "userState")
    @ResponseBody
    public void changeUserState(@RequestParam("userId") String userId){
        adminService.changeUserState(userId);
    }

}
