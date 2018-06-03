package com.example.springtest.dao;

import com.example.springtest.model.Problem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProblemRepository extends JpaRepository<Problem,String> {

    List<Problem> findByUserId(String userId);
}
