package com.example.springtest.Controller;

import com.example.springtest.Services.ProblemService;
import com.example.springtest.Util.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/problem")
public class ProblemController {

    @Autowired
    private ProblemService problemService;


    @PostMapping(value = "getProblemDetail")
    @ResponseBody
    public String getProblemDetail(@RequestParam("problemId") String problemId){
        return  problemService.getProblemDetail(problemId);
    }

    @PostMapping(value = "getProblemTags")
    @ResponseBody
    public String getProblemTags(@RequestParam("problemId") String problemId){
        return  problemService.getProblemTags(problemId);
    }

    @PostMapping(value = "getProblemHistory")
    @ResponseBody
    public String getProblemHistory(@RequestParam("problemId") String problemId){
        return problemService.getProblemHistory(problemId);
    }

    @PostMapping(value = "removeTag")
    @ResponseBody
    public void removeTag(@RequestParam("tagId") String tagId,
                          @RequestParam("problemId") String problemId){

        problemService.removeTag(tagId);

    }

    @PostMapping(value = "addTag")
    @ResponseBody
    public String addTag(@RequestParam("tags[]") String[] tagContent,
                          @RequestParam("problemId") String problemId){

        return problemService.addTag(tagContent,problemId);

    }

    @PostMapping(value = "addHistory")
    @ResponseBody
    public String addTag(@RequestParam("historyContent") String historyContent,
                         @RequestParam("problemId") String problemId){
        return problemService.addHistory(historyContent,problemId);
    }

    /**
     * Test
     * */
    @PostMapping(value = "addProblem")
    @ResponseBody
    public String addProblem(@RequestParam("title") String title,
                             @RequestParam("userId") String userId,
                             @RequestParam("answer") String answer,
                             @RequestParam("description") String description,
                             @RequestParam("tags[]") String[] tags){

        if (userId.equals("")) {
            userId = AppConfig.getUserId();
        }
        return problemService.addProblem(title, userId, answer, description, tags);
    }
}

