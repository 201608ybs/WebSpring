package com.example.springtest.dao;

import com.example.springtest.model.Problem;
import com.example.springtest.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface TagRepository extends JpaRepository<Tag, String>{

    @Query("select t from Tag t where t.problemByProblemId.problemId=:problemId")
    List<Tag> findByProblemId(@Param("problemId") String problemId);

    void deleteTagByTagId(String tagId);

    @Query("select t from Tag t where t.tagContent like :tagContent")
    List<Tag> findTagsByTagContent(@Param("tagContent") String tagContent);
}
