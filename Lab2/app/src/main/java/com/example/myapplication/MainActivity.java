package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication.Controller.NextQuestion;
import com.example.myapplication.Controller.Score;
import com.example.myapplication.Model.AllQuestons;
import com.example.myapplication.Model.Question;
import com.example.myapplication.Summary;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

//Importing Controller
//Importing Model


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        TextView QuestionNumber = findViewById(R.id.QuestionNumber);
        TextView QuestionBox = findViewById(R.id.QuestionBox);
        TextView ScoreBox = findViewById(R.id.ScoretextView);

        Button ButtonFalse = findViewById(R.id.ButtonFalse);
        Button ButtonTrue = findViewById(R.id.ButtonTrue);
        Button ButtonSkip = findViewById(R.id.SkipButton);
        Button ButtonSummary = findViewById(R.id.SummaryButton);

        Score CurrentScore = new Score();
        NextQuestion CurrentQuestion = new NextQuestion();


        //Setting up the first question
        QuestionBox.setText(CurrentQuestion.GetQuestionText());
        QuestionNumber.setText(String.format("Question : %s",(CurrentScore.GetScore() + 1)));
        ScoreBox.setText(String.valueOf(CurrentScore.GetScore()));

        ButtonTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Checking if the Answer is right
             if(CurrentQuestion.CheckAnswer(true)){
                 CurrentScore.CorrectAnswer();
             }
             else{
                 CurrentScore.WrongAnswer();
             }
             ScoreBox.setText(String.valueOf(CurrentScore.GetScore()));
             CurrentQuestion.IncrementCurrentQuestion();

             CheckSwitchToFinalScreen(CurrentQuestion,CurrentScore);

             //Setting up next Question
             QuestionBox.setText(CurrentQuestion.GetQuestionText());
             QuestionNumber.setText(String.format("Question : %s",(CurrentQuestion.GetCurrentQuestionIndex() + 1)));






            }
        });

        ButtonFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Checking if the Answer is right
                if(CurrentQuestion.CheckAnswer(false)){
                    CurrentScore.CorrectAnswer();
                }
                else{
                    CurrentScore.WrongAnswer();
                }
                ScoreBox.setText(String.valueOf(CurrentScore.GetScore()));
                CurrentQuestion.IncrementCurrentQuestion();
                CheckSwitchToFinalScreen(CurrentQuestion,CurrentScore);

                //Setting up next Question
                QuestionBox.setText(CurrentQuestion.GetQuestionText());
                QuestionNumber.setText(String.format("Question : %s",(CurrentQuestion.GetCurrentQuestionIndex() + 1)));




            }
        });

        ButtonSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CurrentScore.SkipQuestion();
                ScoreBox.setText(String.valueOf(CurrentScore.GetScore()));
                CurrentQuestion.IncrementCurrentQuestion();
                CheckSwitchToFinalScreen(CurrentQuestion,CurrentScore);

                //Setting up next Question
                QuestionBox.setText(CurrentQuestion.GetQuestionText());
                QuestionNumber.setText(String.format("Question : %s",(CurrentQuestion.GetCurrentQuestionIndex() + 1)));

            }
        });

        ButtonSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String score = String.valueOf(CurrentScore.GetScore());
                Intent intent = new Intent(MainActivity.this,Summary.class);
                intent.putExtra("Score",score);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }


        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void SwitchToSummary(){
        Intent intent = new Intent(this,Summary.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);

    }

    private void CheckSwitchToFinalScreen(NextQuestion CurrentQuestion,Score CurrentScore){
        if(CurrentQuestion.CheckIfOver()){


            String score = String.valueOf(CurrentScore.GetScore());
            Intent intent = new Intent(MainActivity.this,FinalActivity.class);
            intent.putExtra("Score",score);
            startActivity(intent);

        }

    }
}