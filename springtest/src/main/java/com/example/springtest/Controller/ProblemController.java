package com.example.springtest.Controller;

import com.example.springtest.Services.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
