package com.example.jorda.connect4;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public class HighScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);
        SharedPreferences prefs = this.getSharedPreferences("Connect4HighScores", Context.MODE_PRIVATE);
        int score1 = prefs.getInt("score1", 0);
        int score2 = prefs.getInt("score2", 0);
        int score3 = prefs.getInt("score3", 0);
        int score4 = prefs.getInt("score4", 0);
        int score5 = prefs.getInt("score5", 0);

    }
}
