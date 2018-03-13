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
    Button b_add, b_end;

    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_game);

        tv_score = findViewById(R.id.tv_score);
        b_add = findViewById(R.id.b_add);
        b_end = findViewById(R.id.b_end);

        tv_score.setText(("SCORE " + score));

        b_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score++;
                tv_score.setText(("SCORE " + score));
            }
        });

        b_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("PREFS", 0);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("lastScore", score);
                editor.apply();

                Intent intent = new Intent(getApplicationContext(), HighScoreActivity.class);
                startActivity(intent);
                finish();

            }
        });

    }
}

