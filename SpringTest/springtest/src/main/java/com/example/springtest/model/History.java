package com.example.springtest.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
public class History {
    private String historyId;
    private Date updateTime;
    private String historyContent;
    private Problem problemByProblemId;

    @Id
    @Column(name = "history_id")
    public String getHistoryId() {
        return historyId;
    }

    public void setHistoryId(String historyId) {
        this.historyId = historyId;
    }

    @Basic
    @Column(name = "update_time")
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Basic
    @Column(name = "history_content")
    public String getHistoryContent() {
        return historyContent;
    }

    public void setHistoryContent(String historyContent) {
        this.historyContent = historyContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        History history = (History) o;
        return Objects.equals(historyId, history.historyId) &&
                Objects.equals(updateTime, history.updateTime) &&
                Objects.equals(historyContent, history.historyContent);
    }

    @Override
    public int hashCode() {

        return Objects.hash(historyId, updateTime, historyContent);
    }

    @ManyToOne
    @JoinColumn(name = "problem_id", referencedColumnName = "problem_id")
    public Problem getProblemByProblemId() {
        return problemByProblemId;
    }

    public void setProblemByProblemId(Problem problemByProblemId) {
        this.problemByProblemId = problemByProblemId;
    }
}
