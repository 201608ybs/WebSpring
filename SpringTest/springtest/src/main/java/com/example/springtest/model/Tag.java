package com.example.springtest.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Tag {
    private String tagId;
    private String tagContent;
    private Problem problemByProblemId;

    @Id
    @Column(name = "tag_id")
    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    @Basic
    @Column(name = "tag_content")
    public String getTagContent() {
        return tagContent;
    }

    public void setTagContent(String tagContent) {
        this.tagContent = tagContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return Objects.equals(tagId, tag.tagId) &&
                Objects.equals(tagContent, tag.tagContent);
    }

    @Override
    public int hashCode() {

        return Objects.hash(tagId, tagContent);
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
