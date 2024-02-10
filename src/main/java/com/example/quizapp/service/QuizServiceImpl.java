package com.example.quizapp.service;

import com.example.quizapp.DTO.QuestionDTO;
import com.example.quizapp.exceptions.EmptyQuestionListException;
import com.example.quizapp.model.Question;
import com.example.quizapp.model.Quiz;
import com.example.quizapp.model.Result;
import com.example.quizapp.repository.QuestionRepo;
import com.example.quizapp.repository.QuizRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizServiceImpl implements QuizService {

    private final QuizRepo quizRepo;
    private final QuestionRepo questionRepo;
    private final ModelMapper modelMapper;

    @Autowired
    public QuizServiceImpl(QuizRepo quizRepo, QuestionRepo questionRepo, ModelMapper modelMapper) {
        this.quizRepo = quizRepo;
        this.questionRepo = questionRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public Quiz createQuiz(String category, Integer numOfQues, String title) throws EmptyQuestionListException {
        List<Question> randomQuestionsByCategory = questionRepo.findRandomQuestionsByCategory(category, numOfQues);
        if (!randomQuestionsByCategory.isEmpty()) {
            Quiz newQuiz = new Quiz();
            newQuiz.setQuizTitle(title);
            newQuiz.setQuestionList(randomQuestionsByCategory);
            return quizRepo.save(newQuiz);
        } else throw new EmptyQuestionListException("Questions Not Found with category " + category);
    }

    @Override
    public List<QuestionDTO> getQuestionsByQuizID(Integer quizID) throws EmptyQuestionListException {
        Optional<Quiz> quizOptional = quizRepo.findById(quizID);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        if (quizOptional.isPresent()) {
            Quiz quiz = quizOptional.get();
            List<Question> questionList = quiz.getQuestionList();
            for (Question val : questionList) {
                questionDTOList.add(modelMapper.map(val, QuestionDTO.class));
            }
            return questionDTOList;
        } else throw new EmptyQuestionListException("Quiz not found by passed ID " + quizID);
    }

    @Override
    public Integer submitQuizGetScore(Integer quizID, List<Result> resultList) throws EmptyQuestionListException {
        Optional<Quiz> quizOptional = quizRepo.findById(quizID);
        if (quizOptional.isPresent()) {
            List<Question> questionList = quizOptional.get().getQuestionList();
            int rightCounter = 0;
            int i = 0;
            for (Question val : questionList) {
                if (val.getRightAnswer().equals(resultList.get(i).getCorrectAnswer())) {
                    rightCounter++;
                }
                i++;
            }
            return rightCounter;
        } else throw new EmptyQuestionListException("List not found with quiz ID " + quizID);
    }
}
