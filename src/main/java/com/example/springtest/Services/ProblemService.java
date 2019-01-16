package com.example.springtest.Services;

import javax.transaction.Transactional;

@Transactional
public interface ProblemService {

    String getProblemDetail(String problemId);
    String getProblemTags(String problemId);
    String getProblemHistory(String problemId);
    void removeTag(String tagId);
    String addTag(String[] tagContent,String problemId);
    String addHistory(String historyContent,String problemId);
    String addProblem(String title, String userId, String answer, String description, String[] tags);
}
