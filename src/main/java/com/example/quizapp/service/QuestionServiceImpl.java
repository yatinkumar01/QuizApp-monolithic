package com.example.quizapp.service;

import com.example.quizapp.exceptions.EmptyQuestionListException;
import com.example.quizapp.model.Question;
import com.example.quizapp.repository.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepo questionRepo;

    @Autowired
    public QuestionServiceImpl(QuestionRepo questionRepo) {
        this.questionRepo = questionRepo;
    }

    @Override
    public List<Question> getQuestions() throws EmptyQuestionListException {
        List<Question> questionList = questionRepo.findAll();
        if (!questionList.isEmpty()) return questionList;
        else throw new EmptyQuestionListException("No Questions Found");
    }

    @Override
    public List<Question> getQuestionsByCategory(String category) throws EmptyQuestionListException {
        List<Question> questionList = questionRepo.findByCategoryIgnoreCase(category);
        if (!questionList.isEmpty()) return questionList;
        else throw new EmptyQuestionListException("No Questions Found with category " + category);
    }


}
