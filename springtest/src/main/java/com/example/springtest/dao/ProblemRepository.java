package com.example.springtest.dao;

import com.example.springtest.model.History;
import com.example.springtest.model.Problem;
import com.example.springtest.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ProblemRepository extends JpaRepository<Problem,String> {

    @Query("select p from Problem p where p.userByUserId.userId=:userId")
    List<Problem> findByUserId(@Param("userId") String userId);

    List<Problem> findByProblemId(String problemId);

    @Query("select p from Problem p where p.userByUserId.role=:userRole")
    List<Problem> findByUserRole(@Param("userRole") String userRole);

    @Query("select p from Problem p where p.problemId like :problemId")
    List<Problem> findLikeProblems(@Param("problemId") String problemId);

    @Query("select p from Problem p where p.userByUserId.username like :username")
    List<Problem> findLikeProblemsByUser(@Param("username") String username);

    @Override
    void delete(Problem problem);
}
