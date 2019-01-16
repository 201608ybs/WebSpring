package com.example.springtest.JavaBean;

import java.sql.Date;

public class SimpleHistory {

    private String historyId;
    private String problemId;
    private String content;
    private Date updateTime;

    public String getHistoryId() {
        return historyId;
    }

    public void setHistoryId(String historyId) {
        this.historyId = historyId;
    }

    public String getProblemId() {
        return problemId;
    }

    public void setProblemId(String problemId) {
        this.problemId = problemId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String historyContent) {
        this.content = historyContent;
    }
}
