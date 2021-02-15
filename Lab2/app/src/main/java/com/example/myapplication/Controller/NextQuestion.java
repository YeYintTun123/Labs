package com.example.myapplication.Controller;
import com.example.myapplication.Model.AllQuestons;
import com.example.myapplication.Model.Question;


public class NextQuestion {
    private int CurrentQuestionIndex;
    private AllQuestons Questions;

    public NextQuestion(){

        CurrentQuestionIndex = 0;
        Questions = new AllQuestons();


    }

    public void IncrementCurrentQuestion()
    {
        CurrentQuestionIndex ++;
    }

    public int GetCurrentQuestionIndex()
    {
        return CurrentQuestionIndex;
    }

    public String GetQuestionText()
    {
        return Questions.GetQuestion(CurrentQuestionIndex).getQuestion();
    }

    public boolean CheckAnswer(boolean bTrueVal){
        return (bTrueVal == Questions.CheckAnswerByIndex(CurrentQuestionIndex));
    }

    public boolean CheckIfOver()
    {
        return (CurrentQuestionIndex > Questions.GetNumberOfQuestion());
    }




}
