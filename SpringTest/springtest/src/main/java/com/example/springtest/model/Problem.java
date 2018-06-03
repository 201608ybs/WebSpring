package com.example.springtest.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Problem {
    private String problemId;
    private String description;
    private String answer;
    private int updateTimes;
    private Date createTime;
    private Date lastUpdateTime;
    private String title;
    private User userByUserId;

    @Id
    @Column(name = "problem_id")
    public String getProblemId() {
        return problemId;
    }

    public void setProblemId(String problemId) {
        this.problemId = problemId;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "answer")
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Basic
    @Column(name = "update_times")
    public int getUpdateTimes() {
        return updateTimes;
    }

    public void setUpdateTimes(int updateTimes) {
        this.updateTimes = updateTimes;
    }

    @Basic
    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "last_update_time")
    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Problem problem = (Problem) o;
        return updateTimes == problem.updateTimes &&
                Objects.equals(problemId, problem.problemId) &&
                Objects.equals(description, problem.description) &&
                Objects.equals(answer, problem.answer) &&
                Objects.equals(createTime, problem.createTime) &&
                Objects.equals(lastUpdateTime, problem.lastUpdateTime) &&
                Objects.equals(title, problem.title);
    }

    @Override
    public int hashCode() {

        return Objects.hash(problemId, description, answer, updateTimes, createTime, lastUpdateTime, title);
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }
}
