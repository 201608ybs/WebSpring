package com.example.springtest.Services.serviceImpl;

import com.example.springtest.Services.UserService;
import com.example.springtest.dao.ProblemRepository;
import com.example.springtest.dao.UserRepository;
import com.example.springtest.model.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Resource
    private ProblemRepository problemRepository;

    @Override
    public String registerUser(String userName, String password, String phoneNumber, String email) {
        List<User> result = userRepository.findByUsername(userName);
        Gson gson = new Gson();
        regResponse res = new regResponse();
        if (result.size() > 0){
            res.setSuccessful(false);
            res.setErrMsg("此用户名已被占用");
            String responseStr = gson.toJson(res);
            return responseStr;
        }
        Date date = new Date();
        Byte isRememeber = 1;
        String userId = date.getTime() + "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  hh:mm:ss");
        String dateStr = sdf.format(date);
        User user = new User();
        user.setEmail(email);
        user.setIsRemember(isRememeber);
        user.setPassword(password);
        user.setPhoneNumber(phoneNumber);
        user.setRole("Common");
        user.setState("Normal");
        user.setUserId(userId);
        user.setUsername(userName);
        userRepository.save(user);
        res.setSuccessful(true);
        res.setErrMsg("注册成功");
        String responseStr = gson.toJson(res);
        return  responseStr;
    }

    @Override
    public List<User> logIn(String username, String password){
        List<User> result = userRepository.findByUsernameAndPassword(username, password);
       return result;
    }

    @Override
    public String getUserInfo(String userId){

        List<Problem> result = problemRepository.findByUserId(userId);
        Gson gson = new Gson();

        List<SimpleProblem> problems = new ArrayList<SimpleProblem>();
        InitializeBean ib = new InitializeBean();
        for (int i = 0; i < result.size();++i){
            SimpleProblem sp = new SimpleProblem();
            sp.setTitle(result.get(i).getTitle());
            sp.setProblemId(result.get(i).getProblemId());
            problems.add(sp);
        }
        ib.setProblemTitles(problems);
        ib.setUserId(userId);
        String resStr = gson.toJson(ib);
        return resStr;
    }
}
