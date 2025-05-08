package com.example.harmonix;

public class QuizQuestion {
    int imageResId;
    String question;
    String[] options;
    int correctIndex;

    public QuizQuestion(int imageResId, String question, String[] options, int correctIndex) {
        this.imageResId = imageResId;
        this.question = question;
        this.options = options;
        this.correctIndex = correctIndex;
    }
}
