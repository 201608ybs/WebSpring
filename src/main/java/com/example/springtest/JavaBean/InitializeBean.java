package com.example.springtest.JavaBean;

import java.util.List;

public class InitializeBean {

    private String userId;
    private List<SimpleProblem> problemTitles;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<SimpleProblem> getProblemTitles() {
        return problemTitles;
    }

    public void setProblemTitles(List<SimpleProblem> problemTitles) {
        this.problemTitles = problemTitles;
    }

}
