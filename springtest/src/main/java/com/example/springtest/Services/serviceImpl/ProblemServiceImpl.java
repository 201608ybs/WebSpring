package com.example.springtest.Services.serviceImpl;

import com.example.springtest.JavaBean.ProblemDetail;
import com.example.springtest.JavaBean.SimpleHistory;
import com.example.springtest.JavaBean.SimpleTag;
import com.example.springtest.Services.ProblemService;
import com.example.springtest.dao.HistoryRepository;
import com.example.springtest.dao.ProblemRepository;
import com.example.springtest.dao.TagRepository;
import com.example.springtest.dao.UserRepository;
import com.example.springtest.model.History;
import com.example.springtest.model.Problem;
import com.example.springtest.model.Tag;
import com.example.springtest.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;


@Service
public class ProblemServiceImpl implements ProblemService {

    @Resource
    private ProblemRepository problemRepository;

    @Resource
    private TagRepository tagRepository;

    @Resource
    private HistoryRepository historyRepository;

    @Resource
    private UserRepository userRepository;

    @Override
    public String getProblemDetail(String problemId) {

        List<Problem> result = problemRepository.findByProblemId(problemId);

        String publisher = result.get(0).getUserByUserId().getUsername();
        ProblemDetail detail = new ProblemDetail();
        detail.setAnswer(result.get(0).getAnswer());
        detail.setCreateTime(result.get(0).getCreateTime());
        detail.setDescription(result.get(0).getDescription());
        detail.setLastUpdateTime(result.get(0).getLastUpdateTime());
        detail.setProblemId(result.get(0).getProblemId());
        detail.setPublisher(publisher);
        detail.setTitle(result.get(0).getTitle());
        detail.setUpdateTimes(result.get(0).getUpdateTimes());
        String resStr = new GsonBuilder().setDateFormat("yyyy-MM-dd").create().toJson(detail);
        return resStr;
    }

    @Override
    public  String getProblemTags(String problemId){
        List<Tag> result = tagRepository.findByProblemId(problemId);
        List<SimpleTag> tags = new ArrayList<SimpleTag>();
       for ( Tag tag : result){
            SimpleTag temp = new SimpleTag();
            temp.setTagId(tag.getTagId());
            temp.setTagContent(tag.getTagContent());
            tags.add(temp);
        }
        Gson gson = new Gson();
        String jsonStr = gson.toJson(tags);
        return jsonStr;
    }

    @Override
    public String getProblemHistory(String problemId){
        List<History> result = historyRepository.findByProblemId(problemId);

        List<SimpleHistory> histories = new ArrayList<SimpleHistory>();
        for (int i = 0; i < result.size(); ++i) {
            SimpleHistory history = new SimpleHistory();
            history.setContent(result.get(i).getHistoryContent());
            history.setHistoryId(result.get(i).getHistoryId());
            history.setProblemId(problemId);
            history.setUpdateTime(result.get(i).getUpdateTime());
            histories.add(history);
        }
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        String jsonStr = gson.toJson(histories);
        return jsonStr;
    }

    @Override
    public void removeTag(String tagId){

        tagRepository.deleteTagByTagId(tagId);

    }

    @Override
    public String addTag(String[] tagContent,String problemId){
        Problem curProblem = problemRepository.findByProblemId(problemId).get(0);
        List<SimpleTag> res = new ArrayList<SimpleTag>();
        for (int i = 0; i < tagContent.length;++i){
            Date tagDate = new Date();
            Tag tag = new Tag();
            SimpleTag st = new SimpleTag();
            tag.setProblemByProblemId(curProblem);
            tag.setTagContent(tagContent[i]);
            tag.setTagId(tagDate.getTime() + "");
            st.setTagContent(tagContent[i]);
            st.setTagId(tagDate.getTime() + "");
            tagRepository.save(tag);
            res.add(st);
        }
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        String retStr = gson.toJson(res);
        return retStr;
    }

    @Override
    public String addHistory(String historyContent, String problemId){
        Problem curProblem = problemRepository.findByProblemId(problemId).get(0);
        java.sql.Date curDate =new java.sql.Date(new Date().getTime());
        History history = new History();
        SimpleHistory res = new SimpleHistory();
        history.setHistoryContent(historyContent);
        Date historyDate = new Date();
        history.setHistoryId(historyDate.getTime()+"");
        history.setUpdateTime(curDate);
        history.setProblemByProblemId(curProblem);
        historyRepository.save(history);
        res.setContent(historyContent);
        res.setHistoryId(historyDate.getTime()+"");
        res.setProblemId(problemId);
        res.setUpdateTime(curDate);
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        String retStr = gson.toJson(res);
        curProblem.setUpdateTimes(curProblem.getUpdateTimes()+1);
        curProblem.setLastUpdateTime(curDate);
        problemRepository.saveAndFlush(curProblem);
        return retStr;
    }

    public String addProblem(String title, String userId, String answer, String description, String[] tags){

        java.sql.Date curDate =new java.sql.Date(new Date().getTime());
        Date date = new Date();
        String problemId = date.getTime()+"";
        Problem problem = new Problem();
        problem.setAnswer(answer);
        problem.setCreateTime(curDate);
        problem.setDescription(description);
        problem.setLastUpdateTime(curDate);
        problem.setProblemId(problemId);
        problem.setTitle(title);
        problem.setUpdateTimes(1);
        History history = new History();
        history.setHistoryContent(answer);
        Date historyDate = new Date();
        history.setHistoryId(historyDate.getTime()+"");
        history.setProblemByProblemId(problem);
        history.setUpdateTime(curDate);
        User user = userRepository.findByUserId(userId).get(0);
        problem.setUserByUserId(user);
        problemRepository.saveAndFlush(problem);
        historyRepository.save(history);
        if(tags.length != 0) {
            for (String temp : tags) {
                Date tagDate = new Date();
                Tag tag = new Tag();
                tag.setProblemByProblemId(problem);
                tag.setTagContent(temp);
                tag.setTagId(tagDate.getTime() + "");
                tagRepository.save(tag);
            }
        }
        ProblemDetail detail = new ProblemDetail();
        detail.setAnswer(problem.getAnswer());
        detail.setCreateTime(problem.getCreateTime());
        detail.setDescription(problem.getDescription());
        detail.setLastUpdateTime(problem.getLastUpdateTime());
        detail.setProblemId(problem.getProblemId());
        detail.setPublisher(problem.getUserByUserId().getUsername());
        detail.setTitle(problem.getTitle());
        detail.setUpdateTimes(problem.getUpdateTimes());
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        String retStr = gson.toJson(detail);
        return retStr;
    }
}
