package com.example.jorda.connect4;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.util.Log;
import android.widget.Toast;

public class HighScoreActivity extends AppCompatActivity {

    TextView tv_score;

    int lastScore;
    int best1, best2, best3;
    String p1, p2, p3;
    String currentPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        tv_score = findViewById(R.id.tv_score);

        Intent i = getIntent();
        currentPlayer = i.getStringExtra("player1");
        Log.wtf("TAG", currentPlayer);
        SharedPreferences preferences = getSharedPreferences("PREFS", 0);
        lastScore = preferences.getInt("lastScore", 0);
        best1 = preferences.getInt("best1", 0);
        best2 = preferences.getInt("best2", 0);
        best3 = preferences.getInt("best3", 0);
        p1 = preferences.getString("first", "-");
        p2 = preferences.getString("second", "-");
        p3 = preferences.getString("third", "-");

        SharedPreferences.Editor editor = preferences.edit();

        if((best1==best2)&&(best2==best3)&&(best1<=lastScore)){ //because at first every value is the same, 0
            //Toast.makeText(HighScoreActivity.this,"3 : "+best3,Toast.LENGTH_SHORT).show();
            best1 = lastScore;
            p1 = currentPlayer;

            editor.putInt("best1", best1);
            editor.putString("first", p1);
            editor.apply();
        }else if((best2==best3)&&(best2<lastScore)&&(best1>lastScore)){
            //Toast.makeText(HighScoreActivity.this,"2 : "+best3,Toast.LENGTH_SHORT).show();
            best2 = lastScore;
            p2 = currentPlayer;

            editor.putInt("best2", best1);
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
        }else{
            if((lastScore > best3)&&(lastScore!=best2)&&(lastScore!=best1)) {
                //Toast.makeText(HighScoreActivity.this,"Test : "+best3,Toast.LENGTH_SHORT).show();
                best3 = lastScore;
                p3 = currentPlayer;

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

                //Log.wtf("best3 before temp2", Integer.toString(best3));
                //best3 = temp2;
                //p3 = prevP2;
                //Toast.makeText(HighScoreActivity.this,"Test3 : "+best3,Toast.LENGTH_SHORT).show();
                //SharedPreferences.Editor editor = preferences.edit();
                //editor.putInt("best3", best3);
                //editor.putString("third", p3);
                editor.putInt("best2", best2);
                editor.putString("second", p2);
                editor.putInt("best1", best1);
                editor.putString("first", p1);
                editor.apply();

            }
        }

        tv_score.setText("LAST GAME- " + currentPlayer + ": " + lastScore + "\n" +
                "1. : " + p1 + ": " + best1 + "\n" +
                "2. : " + p2 + ": " + best2 + "\n" +
                "3. : " + p3 + ": " + best3);




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
