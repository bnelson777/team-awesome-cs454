package com.example.jorda.connect4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class HomeScreenActivity extends AppCompatActivity {

    private Button localGame;
    private Button onlineGame;
    private Button highScores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        localGame = findViewById(R.id.btnLocalGame);
        onlineGame = findViewById(R.id.btnOnlineGame);
        highScores = findViewById(R.id.btnHighScores);
        localGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeScreenActivity.this, MenuActivity.class));
            }
        });
        onlineGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeScreenActivity.this, OnlineActivity.class));
            }
        });
        highScores.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeScreenActivity.this, DemoGameActivity.class));
            }
        });
    }
}
