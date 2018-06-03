package com.example.springtest.JavaBean;

import java.sql.Date;

public class ProblemDetail {

    private String problemId;
    private String title;
    private Date createTime;
    private Date lastUpdateTime;
    private String answer;
    private String description;
    private String publisher;
    private int updateTimes;

    public void setProblemId(String problemId) {
        this.problemId = problemId;
    }

    public String getProblemId() {
        return problemId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setUpdateTimes(int updateTimes) {
        this.updateTimes = updateTimes;
    }

    public int getUpdateTimes() {
        return updateTimes;
    }
}
