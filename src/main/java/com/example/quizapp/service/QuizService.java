package com.example.quizapp.service;

import com.example.quizapp.DTO.QuestionDTO;
import com.example.quizapp.exceptions.EmptyQuestionListException;
import com.example.quizapp.model.Quiz;
import com.example.quizapp.model.Result;

import java.util.List;

public interface QuizService {

    Quiz createQuiz(String category,Integer numOfQues,String title) throws EmptyQuestionListException;

    List<QuestionDTO> getQuestionsByQuizID(Integer quizID) throws EmptyQuestionListException;

    Integer submitQuizGetScore(Integer quizID, List<Result> resultList) throws EmptyQuestionListException;

}
