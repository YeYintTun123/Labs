package com.example.myapplication.Controller;
import com.example.myapplication.Model.AllQuestons;

public class Score {
    private final int CorrectAnswerPoint = 10;
    private final int WrongAnswer = -10;
    private final int SkipQuestionPoint = -5;
    private static int CurrentScore;

    public Score()
    {
        CurrentScore = 0;
    }

    public void CorrectAnswer(){CurrentScore += CorrectAnswerPoint;}
    public void WrongAnswer(){CurrentScore += WrongAnswer;}
    public void SkipQuestion(){CurrentScore += SkipQuestionPoint;}
    public int GetScore(){return CurrentScore;}


}
