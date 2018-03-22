package com.example.jorda.connect4;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import android.content.Intent;
import android.widget.Button;

/**
 * Created by jorda on 2/17/2018.
 */

public class GamePlayActivity extends AppCompatActivity {
    private GamePlayController mController;
    //final private GamePlayView mGamePlayView;
    private View mGamePlayView;
    private MediaPlayer mp;
    private Button musicToggle;
    String currentPlayer;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Intent intent = getIntent();
            currentPlayer = intent.getStringExtra("currentPlayer");
            MenuController menuController = (MenuController)intent.getSerializableExtra("MenuController");

            if (menuController.getBoard_size().equals("Small")) {
                setContentView(R.layout.activity_gameplay_small);

                GamePlayViewSmall boardView =  findViewById(R.id.gameViewSmall);
                mController = new GamePlayController(this, boardView, menuController, currentPlayer);

            } else if (menuController.getBoard_size().equals("Medium")) {
                setContentView(R.layout.activity_gameplay_medium);
                GamePlayViewMedium boardView =  findViewById(R.id.gameViewMedium);
                mController = new GamePlayController(this, boardView, menuController, currentPlayer);

            } else {
                setContentView(R.layout.activity_gameplay_large);
                GamePlayViewLarge boardView =  findViewById(R.id.gameViewLarge);
                mController = new GamePlayController(this, boardView, menuController, currentPlayer);
            }

            musicToggle = findViewById(R.id.musicToggle);
            mp=MediaPlayer.create(getApplicationContext(),R.raw.jeopardy);// the song is a filename which i have pasted inside a folder **raw** created under the **res** folder.//
            mp.start();
            mp.setLooping(true);

            musicToggle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mp.isPlaying())
                        mp.pause();
                    else
                        mp.start();
                }
            });

            //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            //getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_close);
        }

    @Override
    protected void onDestroy()
    {
        mp.release();
        super.onDestroy();
    }
}
