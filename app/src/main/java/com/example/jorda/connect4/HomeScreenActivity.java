package com.example.jorda.connect4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.util.Log;

public class HomeScreenActivity extends AppCompatActivity {

    private Button localGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Log.wtf("Home Screen Activity", "OnCreate");
        localGame = findViewById(R.id.btnLocalGame);
        Log.wtf("Home Screen Activity", "OnCreate");
        localGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeScreenActivity.this, MenuActivity.class));
            }
        });
    }
}
