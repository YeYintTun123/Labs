package com.example.myapplication.Model;

import com.example.myapplication.R;

public class AllQuestons
{

    private Question[] AllQuestion = {
            new Question("Are you ready(Trick Question)",true),
            new Question("1 + 1 = 2 ",true),
            new Question("8 * 8 = 63",false),
            new Question("5! = 120",true),
            new Question("7! = 5040",true),
            new Question("8! = 40320",true),
            new Question("4^4 = 42",false),
            new Question("7^2 = 46",false),
            new Question("7^2 = 49",true),
            new Question("8^2 = 64",true),
    };
    private int TotalNumberOfQuestion = 7;

    public Question GetQuestion(int Index)
    {
        return AllQuestion[Index];
    }

    public int GetNumberOfQuestion()
    {
        return TotalNumberOfQuestion;
    }

    public boolean CheckAnswerByIndex(int index){

        return   AllQuestion[index].IsQuestion();
    }
}
