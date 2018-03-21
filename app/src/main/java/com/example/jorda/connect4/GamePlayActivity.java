package com.example.jorda.connect4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import android.content.Intent;

/**
 * Created by jorda on 2/17/2018.
 */

public class GamePlayActivity extends AppCompatActivity {
    private GamePlayController mController;
    //final private GamePlayView mGamePlayView;
    private View mGamePlayView;
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

            //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            //getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_close);
        }
}
