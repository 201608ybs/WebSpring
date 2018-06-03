package com.example.springtest.model;

import java.util.List;
import java.util.Set;

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
