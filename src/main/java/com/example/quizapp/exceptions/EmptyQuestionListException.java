package com.example.quizapp.exceptions;

public class EmptyQuestionListException extends Exception {

    public EmptyQuestionListException() {
    }

    public EmptyQuestionListException(String message) {
        super(message);
    }
}
