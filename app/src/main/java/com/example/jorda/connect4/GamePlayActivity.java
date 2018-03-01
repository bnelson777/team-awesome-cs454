package com.example.jorda.connect4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jorda.connect4.R;
import android.content.Intent;

/**
 * Created by jorda on 2/17/2018.
 */

public class GamePlayActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameplay);

        Intent intent = getIntent();
        MenuController menuController = (MenuController)intent.getSerializableExtra("MenuController");

        //Test the chosen board size
        //Toast.makeText(GamePlayActivity.this,"Test : "+menuController.getPlayer2_color(),Toast.LENGTH_SHORT).show();

    }
}
