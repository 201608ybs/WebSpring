package com.example.springtest.Services.serviceImpl;

import com.example.springtest.JavaBean.ProblemDetail;
import com.example.springtest.JavaBean.SimpleHistory;
import com.example.springtest.JavaBean.SimpleTag;
import com.example.springtest.Services.ProblemService;
import com.example.springtest.dao.HistoryRepository;
import com.example.springtest.dao.ProblemRepository;
import com.example.springtest.dao.TagRepository;
import com.example.springtest.model.History;
import com.example.springtest.model.Problem;
import com.example.springtest.model.Tag;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Service
public class ProblemServiceImpl implements ProblemService {

    @Resource
    private ProblemRepository problemRepository;

    @Resource
    private TagRepository tagRepository;

    @Resource
    private HistoryRepository historyRepository;

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
}
