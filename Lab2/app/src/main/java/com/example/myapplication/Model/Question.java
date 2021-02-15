package com.example.myapplication.Model;

public class Question {
    private String QuestionText;
    private boolean bAnswerIsTrue;

    public Question(String Question, boolean IsTrue)
    {
        this.QuestionText= Question;
        bAnswerIsTrue = IsTrue;
    }

    public String getQuestion(){
        return QuestionText;
    }

    public boolean IsQuestion()
    {
        return bAnswerIsTrue;

    }
}
