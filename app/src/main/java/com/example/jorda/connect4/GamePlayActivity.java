package com.example.jorda.connect4;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
    private int mColumns;
    private int mRows;
    private int mTops[]; // Current tops of each column
    private GamePlayController mController;
    //final private GamePlayView mGamePlayView;
    private View mGamePlayView;
    private int currentRound;
    private int mBoard[][];

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_gameplay);

            Intent intent = getIntent();
            MenuController menuController = (MenuController)intent.getSerializableExtra("MenuController");
            mColumns = menuController.getBoard_column();
            mRows = menuController.getBoard_row();
            mTops = new int[mColumns];
            mBoard = new int[mColumns][mRows];
            currentRound = menuController.getNum_rounds();

            Log.wtf("GamePlayActivity", menuController.getPlayer1_color()); //if the color is black, it goes first
            Log.wtf("GamePlayActivity", menuController.getPlayer2_color());
            Log.wtf("GamePlayActivity", menuController.getPlayer1());
            Log.wtf("GamePlayActivity", menuController.getPlayer2());
            Log.wtf("GamePlayActivity", menuController.getPlayer1_difficulty());
            Log.wtf("GamePlayActivity", menuController.getPlayer2_difficulty());
            Log.wtf("GamePlayActivity", menuController.getBoard_size());
            Log.wtf("GamePlayActivity", ""+menuController.getNum_rounds()); //needed to be implemented
            Log.wtf("GamePlayActivity", menuController.getFirst_player()); //Either player1 or player2
            Log.wtf("GamePlayActivity", ""+menuController.getBoard_row());
            Log.wtf("GamePlayActivity", ""+menuController.getBoard_column());

            GamePlayView boardView = (GamePlayView) findViewById ( R.id.gameView1 );
            mController = new GamePlayController(this, boardView, mColumns, mRows);

            //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            //getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_close);
        }
}
