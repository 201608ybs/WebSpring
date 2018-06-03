package com.example.springtest.Controller;

import com.example.springtest.JavaBean.LogInResponse;
import com.example.springtest.model.User;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/user")
public class RegisterController {

    @Autowired
    private com.example.springtest.Services.UserService userService;


    @PostMapping(value = "register")
    @ResponseBody
    public String registerUser(@RequestParam("userName") String username,
                               @RequestParam("password") String password,
                               @RequestParam("phoneNumber") String phone,
                               @RequestParam("email") String email) {

        return userService.registerUser(username,password,phone,email);
    }

    @PostMapping(value = "login")
    @ResponseBody
    public String logIn(@RequestParam("name") String username,
                               @RequestParam("pwd") String password,
                        HttpServletResponse response) {
        Gson gson = new Gson();
        LogInResponse res = new LogInResponse();
        Boolean isValid = false;
        List<User> result = userService.logIn(username,password);
        if (result.size() > 0) {
            isValid = true;
            res.setRole(result.get(0).getRole());
            res.setState(result.get(0).getState());
            res.setSuccessful(isValid);
            String id = result.get(0).getUserId();
            Cookie cookie = new Cookie("userId",id);
            response.addCookie(cookie);
        }
        else {
            res.setRole("");
            res.setState("");
            res.setSuccessful(isValid);
        }
        String resStr = gson.toJson(res);
        return resStr;
    }

    @GetMapping(value = "getUserInfo")
    @ResponseBody
    public String getUserInfo(HttpServletRequest request){
        Cookie cookies[] = request.getCookies();
        String id = "";
        for(Cookie c :cookies ){
            if (c.getName().equals("userId")) {
                id = c.getValue();
            }
        }
        return userService.getUserInfo(id);
    }

}
