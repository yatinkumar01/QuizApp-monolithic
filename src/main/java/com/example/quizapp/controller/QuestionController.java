package com.example.quizapp.controller;

import com.example.quizapp.exceptions.EmptyQuestionListException;
import com.example.quizapp.model.Question;
import com.example.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/questions")
    ResponseEntity<List<Question>> getQuestions() throws EmptyQuestionListException {
        return new ResponseEntity<>(questionService.getQuestions(), HttpStatus.OK);
    }

    @GetMapping("/category/{category}")
    ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String category) throws EmptyQuestionListException {
        return new ResponseEntity<>(questionService.getQuestionsByCategory(category),HttpStatus.FOUND);
    }



}
