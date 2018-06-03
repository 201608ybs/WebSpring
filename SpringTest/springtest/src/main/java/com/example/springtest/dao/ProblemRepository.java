package com.example.springtest.dao;

import com.example.springtest.model.Problem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProblemRepository extends JpaRepository<Problem,String> {

    @Query("select p from Problem p where p.userByUserId.userId=:userId")
    List<Problem> findByUserId(@Param("userId") String sex);
}
