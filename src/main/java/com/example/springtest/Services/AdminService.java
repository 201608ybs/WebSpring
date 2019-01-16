package com.example.springtest.Services;

import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;

@Transactional
public interface AdminService {

    String getAdminProblem();
    String getProblemByTag(String tagContent);
    String getProblemById(String problemId);
    String getProblemByUser(String userName);
    String getAllProblem();
    String getAllUser();
    String updateUserInfo(String userId, String username, String password, String phone, String email);
    void changeUserState(String userId);
    void deleteProblem(String problemId);
    String updateProblem(String problemId, String title, String answer, String description);
}
