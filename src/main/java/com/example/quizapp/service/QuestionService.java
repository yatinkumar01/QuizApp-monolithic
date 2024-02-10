package com.example.quizapp.service;

import com.example.quizapp.exceptions.EmptyQuestionListException;
import com.example.quizapp.model.Question;

import java.util.List;

public interface QuestionService {

    List<Question> getQuestions() throws EmptyQuestionListException;

    List<Question> getQuestionsByCategory(String category) throws EmptyQuestionListException;
}
