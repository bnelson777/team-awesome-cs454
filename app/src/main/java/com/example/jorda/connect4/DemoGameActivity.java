package com.example.jorda.connect4;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;


public class DemoGameActivity extends AppCompatActivity {

    TextView tv_score;
    Button b_add, b_end, b_end1;

    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_game);

        tv_score = findViewById(R.id.tv_score);
        //b_add = findViewById(R.id.b_add);
        b_end = findViewById(R.id.b_end);

        Intent i = getIntent();
        final String player1_name = i.getStringExtra("player1");
        final String player2_name = i.getStringExtra("player2");
        final String player1_final = i.getStringExtra("player1_final");
        final String player2_final = i.getStringExtra("player2_final");

        final int highScore;
        final String highScorePlayer;

        tv_score.setText((player1_name+" : " +player1_final+"\n"+player2_name+" : " +player2_final));

        /*b_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = getIntent();
                Intent intent = new Intent(DemoGameActivity.this, MenuActivity.class);
                intent.putExtra("player1", player1_name);
                startActivity(intent);
                finish();
            }
        });*/

        if(Integer.parseInt(player1_final)>=Integer.parseInt(player2_final)){
            highScore=Integer.parseInt(player1_final);
            highScorePlayer=player1_name;
        }else{
            highScore=Integer.parseInt(player2_final);
            highScorePlayer=player2_name;
        }

        b_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("PREFS", 0);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("lastScore", highScore);
                editor.apply();
                //Intent i = getIntent();
                //String player1 =  i.getStringExtra("player2_name");
                Intent intent = new Intent(DemoGameActivity.this, HighScoreActivity.class);
                intent.putExtra("main_player", player1_name);
                intent.putExtra("player1", highScorePlayer);
                startActivity(intent);
                finish();

            }
        });

        /*b_end1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("PREFS", 0);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("lastScore", Integer.parseInt(player2_final));
                editor.apply();
                //Intent i = getIntent();
                //String player1 =  i.getStringExtra("player1_name");
                Intent intent = new Intent(DemoGameActivity.this, HighScoreActivity.class);
                intent.putExtra("player1", player1_name);
                startActivity(intent);
                finish();

            }
        });*/

    }
}

