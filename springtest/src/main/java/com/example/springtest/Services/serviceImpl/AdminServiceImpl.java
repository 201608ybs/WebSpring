package com.example.springtest.Services.serviceImpl;

import com.example.springtest.JavaBean.AllProblemBean;
import com.example.springtest.JavaBean.ProblemDetail;
import com.example.springtest.JavaBean.UserBean;
import com.example.springtest.Services.AdminService;
import com.example.springtest.dao.ProblemRepository;
import com.example.springtest.dao.TagRepository;
import com.example.springtest.dao.UserRepository;
import com.example.springtest.model.Problem;
import com.example.springtest.model.Tag;
import com.example.springtest.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Service
public class AdminServiceImpl implements AdminService{

    @Resource
    private ProblemRepository problemRepository;

    @Resource
    private TagRepository tagRepository;

    @Resource
    private UserRepository userRepository;

    @Override
    public String getAdminProblem(){
        List<Problem> result = problemRepository.findByUserRole("Admin");

        List<ProblemDetail> problems = new ArrayList<ProblemDetail>();
        for (int i = 0;i < result.size();++i){
            String publisher = result.get(i).getUserByUserId().getUsername();
            ProblemDetail detail = new ProblemDetail();
            detail.setAnswer(result.get(i).getAnswer());
            detail.setCreateTime(result.get(i).getCreateTime());
            detail.setDescription(result.get(i).getDescription());
            detail.setLastUpdateTime(result.get(i).getLastUpdateTime());
            detail.setProblemId(result.get(i).getProblemId());
            detail.setPublisher(publisher);
            detail.setTitle(result.get(i).getTitle());
            detail.setUpdateTimes(result.get(i).getUpdateTimes());
            problems.add(detail);
        }
        String resStr = new GsonBuilder().setDateFormat("yyyy-MM-dd").create().toJson(problems);
        return resStr;
    }

    @Override
    public String getProblemByTag(String tagContent){
        List<Tag> tagList = tagRepository.findTagsByTagContent("%"+tagContent+"%");
        List<AllProblemBean> problems = new ArrayList<AllProblemBean>();
        if (tagList.size() != 0){
            String problemId = tagList.get(0).getProblemByProblemId().getProblemId();
            List<Problem> result = problemRepository.findByProblemId(problemId);
            problems = buildList(result, problemId);
        }
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        String resStr = gson.toJson(problems);
        return resStr;
    }

    @Override
    public String getProblemById(@RequestParam("problemId") String problemId){
        List<AllProblemBean> problems = new ArrayList<AllProblemBean>();
        List<Problem> result = problemRepository.findLikeProblems("%"+problemId+"%");
        if (result.size() != 0){
            problems = buildList(result,result.get(0).getProblemId());
        }
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        String resStr = gson.toJson(problems);
        return resStr;
    }

    @Override
    public String getProblemByUser(String userName){
        List<Problem> result = problemRepository.findLikeProblemsByUser("%"+userName+"%");
        List<AllProblemBean> problems = new ArrayList<AllProblemBean>();
        if (result.size() != 0){
            problems = buildList(result,result.get(0).getProblemId());
        }
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        String resStr = gson.toJson(problems);
        return resStr;
    }

    @Override
    public String getAllProblem(){
        List<Problem> result = problemRepository.findAll();
        List<AllProblemBean> problems = new ArrayList<AllProblemBean>();
        if (result.size() != 0){
            problems = buildList(result,result.get(0).getProblemId());
        }
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        String resStr = gson.toJson(problems);
        return resStr;
    }

    private List<AllProblemBean> buildList(List<Problem> result, String problemId){
        List<AllProblemBean> problems = new ArrayList<AllProblemBean>();
        for (int i = 0;i < result.size();++i){
            AllProblemBean ap = new AllProblemBean();
            ap.setAnswer(result.get(i).getAnswer());
            ap.setCreateTime(result.get(i).getCreateTime());
            ap.setDescription(result.get(i).getDescription());
            ap.setProblemId(result.get(i).getProblemId());
            ap.setPublisher(result.get(i).getUserByUserId().getUsername());
            List<Tag> tags = tagRepository.findByProblemId(problemId);
            String tagStr = "";
            for (Tag tag:tags){
                tagStr = tagStr+" "+tag.getTagContent();
            }
            ap.setTags(tagStr);
            ap.setTitle(result.get(i).getTitle());
            ap.setUpdateTimes(result.get(i).getUpdateTimes());
            problems.add(ap);
        }
        return problems;
    }

    @Override
    public String getAllUser(){
        List<User> result = userRepository.findByRole("Common");
        List<UserBean> users = new ArrayList<UserBean>();
        for (int i = 0;i < result.size();++i){
            UserBean ub = new UserBean();
            ub.setEmail(result.get(i).getEmail());
            ub.setPassword(result.get(i).getPassword());
            ub.setPhoneNumber(result.get(i).getPhoneNumber());
            ub.setState(result.get(i).getState());
            ub.setUserId(result.get(i).getUserId());
            ub.setUserName(result.get(i).getUsername());
            users.add(ub);
        }
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        String resStr = gson.toJson(users);
        return resStr;
    }

    @Override
    public String updateUserInfo(String userId, String username, String password, String phone, String email){
        User result = userRepository.findByUserId(userId).get(0);
        result.setEmail(email);
        result.setPassword(password);
        result.setPhoneNumber(phone);
        result.setUsername(username);
        userRepository.save(result);
        UserBean ub = new UserBean();
        ub.setUserName(result.getUsername());
        ub.setUserId(result.getUserId());
        ub.setState(result.getState());
        ub.setPhoneNumber(result.getPhoneNumber());
        ub.setPassword(result.getPassword());
        ub.setEmail(result.getEmail());
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        String resStr = gson.toJson(ub);
        return resStr;
    }

    @Override
    public void changeUserState(String userId){
        User user = userRepository.findByUserId(userId).get(0);
        if(user.getState().equals("Normal")){
            user.setState("Disabled");
        }
        else{
            user.setState("Normal");
        }
        userRepository.save(user);
    }
}
