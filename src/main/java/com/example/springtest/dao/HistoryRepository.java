package com.example.springtest.dao;

import com.example.springtest.model.History;
import com.example.springtest.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface HistoryRepository extends JpaRepository<History,String>{

    @Query("select h from History h where h.problemByProblemId.problemId=:problemId")
    List<History> findByProblemId(@Param("problemId") String problemId);
}
