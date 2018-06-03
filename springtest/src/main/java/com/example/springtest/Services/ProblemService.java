package com.example.springtest.Services;

public interface ProblemService {

    String getProblemDetail(String problemId);
    String getProblemTags(String problemId);
    String getProblemHistory(String problemId);

}
