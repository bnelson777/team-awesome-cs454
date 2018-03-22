package com.example.jorda.connect4;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;


public class HighScoreActivity extends AppCompatActivity {

    TextView tv_score;
    Button btnClear, menu;

    int lastScore;
    int best1, best2, best3, best4, best5;
    String p1, p2, p3, p4, p5;
    String currentPlayer;
    String main_player;

    public void addscore(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        tv_score = findViewById(R.id.tv_score);
        btnClear = findViewById(R.id.btnClear);
        menu = findViewById(R.id.menu);

        Intent i = getIntent();
        currentPlayer = i.getStringExtra("player1");
        main_player = i.getStringExtra("main_player");
        Log.wtf("TAG", currentPlayer);
        SharedPreferences preferences = getSharedPreferences("PREFS", 0);
        lastScore = preferences.getInt("lastScore", 0);

        best1 = preferences.getInt("best1", 0);
        best2 = preferences.getInt("best2", 0);
        best3 = preferences.getInt("best3", 0);
        best4 = preferences.getInt("best4", 0);
        best5 = preferences.getInt("best5", 0);

        p1 = preferences.getString("first", "-");
        p2 = preferences.getString("second", "-");
        p3 = preferences.getString("third", "-");
        p4 = preferences.getString("four", "-");
        p5 = preferences.getString("five", "-");

        SharedPreferences.Editor editor = preferences.edit();

        //persons.add(new Person(currentPlayer, lastScore));
        //Collections.sort(persons);

        //for(int x=0; x<persons.size(); x++){
        //    Log.wtf("persons",""+ persons.get(x).name+" : "+persons.get(x).score);
        //}



        if((best1==best2)&&(best2==best3)&&(best1<=lastScore)){ //because at first every value is the same, 0
            //Toast.makeText(HighScoreActivity.this,"3 : "+best3,Toast.LENGTH_SHORT).show();
            best1 = lastScore;
            p1 = currentPlayer;

            editor.putInt("best1", best1);
            editor.putString("first", p1);
            editor.apply();
            Toast.makeText(HighScoreActivity.this,"You have reached the highest score!",Toast.LENGTH_SHORT).show();
        }else if((best2==best3)&&(best2<lastScore)&&(best1>lastScore)){
            //Toast.makeText(HighScoreActivity.this,"2 : "+best3,Toast.LENGTH_SHORT).show();
            best2 = lastScore;
            p2 = currentPlayer;

            editor.putInt("best2", best2);
            editor.putString("second", p2);
            editor.apply();
        }else if((best2==best3)&&(best2<lastScore)&&(best1<lastScore)){
            //Toast.makeText(HighScoreActivity.this,"1 : "+best3,Toast.LENGTH_SHORT).show();
            int temp = best1;
            String prevP = p1;
            best1 = lastScore;
            p1 = currentPlayer;
            best2 = temp;
            p2 = prevP;
            editor.putInt("best2", best2);
            editor.putString("second", p2);
            editor.putInt("best1", best1);
            editor.putString("first", p1);
            editor.apply();
            Toast.makeText(HighScoreActivity.this,"You have reached the highest score!",Toast.LENGTH_SHORT).show();
        }else{
            if((lastScore > best5)&&(lastScore!=best3)&&(lastScore!=best2)&&(lastScore!=best1)&&(lastScore!=best4)){
                best5 = lastScore;
                p5 = currentPlayer;

                editor.putInt("best5", best5);
                editor.putString("fifth", p5);
                editor.apply();
            }
            if((lastScore > best4)&&(lastScore!=best3)&&(lastScore!=best2)&&(lastScore!=best1)){
                int temp = best4;
                String prevP = p4;
                best4 = lastScore;
                p4 = currentPlayer;
                best5 = temp;
                p5 = prevP;
                //Toast.makeText(HighScoreActivity.this,"Test : "+best3,Toast.LENGTH_SHORT).show();
                // SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("best5", best5);
                editor.putString("fifth", p5);
                editor.putInt("best4", best4);
                editor.putString("fourth", p4);
                editor.apply();
            }
            if((lastScore > best3)&&(lastScore!=best2)&&(lastScore!=best1)) {
                int temp = best3;
                String prevP = p3;
                best3 = lastScore;
                p3 = currentPlayer;
                best4 = temp;
                p4 = prevP;
                //Toast.makeText(HighScoreActivity.this,"Test : "+best3,Toast.LENGTH_SHORT).show();
                // SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("best4", best4);
                editor.putString("fourth", p4);
                editor.putInt("best3", best3);
                editor.putString("third", p3);
                editor.apply();
            }
            if((lastScore > best2)&&(lastScore!=best1)) {
                int temp = best2;
                String prevP = p2;
                best2 = lastScore;
                p2 = currentPlayer;
                best3 = temp;
                p3 = prevP;
                //Toast.makeText(HighScoreActivity.this,"Test : "+best3,Toast.LENGTH_SHORT).show();
                // SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("best3", best3);
                editor.putString("third", p3);
                editor.putInt("best2", best2);
                editor.putString("second", p2);
                editor.apply();
            }
            if(lastScore > best1) {
                //Toast.makeText(HighScoreActivity.this,"Test2 : "+best3,Toast.LENGTH_SHORT).show();
                int temp = best1;
                String prevP = p1;
                best1 = lastScore;
                p1 = currentPlayer;

                int temp2 = best2;
                String prevP2 = p2;
                best2 = temp;
                p2 = prevP;

                editor.putInt("best2", best2);
                editor.putString("second", p2);
                editor.putInt("best1", best1);
                editor.putString("first", p1);
                editor.apply();
                Toast.makeText(HighScoreActivity.this,"You have reached the highest score!",Toast.LENGTH_SHORT).show();

            }
        }

        tv_score.setText("\n\nLast game - " + currentPlayer + " : " + lastScore + "\n\n\n" +
                "1. : " + p1 + ": " + best1 + "\n" +
                "2. : " + p2 + ": " + best2 + "\n" +
                "3. : " + p3 + ": " + best3 + "\n" +
                "4. : " + p3 + ": " + best4 + "\n" +
                "5. : " + p3 + ": " + best5);

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("PREFS", 0);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear().apply();
                tv_score.setText("\n\nLast game - " + currentPlayer + " : " + lastScore + "\n\n\n" +
                        "1. : - : 0\n" +
                        "2. : - : 0\n" +
                        "3. : - : 0\n" +
                        "4. : - : 0\n" +
                        "5. : - : 0\n");
            }
        });
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = getIntent();
                Intent intent = new Intent(HighScoreActivity.this, HomeScreenActivity.class);
                //intent.putExtra("player1", main_player);
                startActivity(intent);
                finish();
            }
        });



    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        Intent intent = new Intent(HighScoreActivity.this, DemoGameActivity.class);
        intent.putExtra("player1", currentPlayer);
        startActivity(intent);
        finish();
    }
}
