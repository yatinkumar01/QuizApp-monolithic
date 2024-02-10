package com.example.quizapp.controller;

import com.example.quizapp.DTO.QuestionDTO;
import com.example.quizapp.exceptions.EmptyQuestionListException;
import com.example.quizapp.model.Quiz;
import com.example.quizapp.model.Result;
import com.example.quizapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    private final QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping("/createQuiz")
    ResponseEntity<Quiz> createQuiz(@RequestParam String category, @RequestParam Integer numOfQues, @RequestParam String title) throws EmptyQuestionListException {
        return new ResponseEntity<>(quizService.createQuiz(category, numOfQues, title), HttpStatus.OK);
    }

    @GetMapping("/questions/{quizID}")
    ResponseEntity<List<QuestionDTO>> getQuestionsByQuizID(@PathVariable Integer quizID) throws EmptyQuestionListException {
        return new ResponseEntity<>(quizService.getQuestionsByQuizID(quizID),HttpStatus.FOUND);
    }


    @PostMapping("/submit/{quizID}")
    ResponseEntity<Integer> submitQuizGetScore(@PathVariable Integer quizID,@RequestBody List<Result> resultList) throws EmptyQuestionListException{
        return new ResponseEntity<>(quizService.submitQuizGetScore(quizID,resultList),HttpStatus.OK);
    }

}
