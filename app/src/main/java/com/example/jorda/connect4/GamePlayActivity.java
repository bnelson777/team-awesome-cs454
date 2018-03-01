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
        Log.wtf("GamePlayActivity", "onCreate");

        Intent intent = getIntent();
        MenuController menuController = (MenuController)intent.getSerializableExtra("MenuController");

        setContentView(R.layout.activity_gameplay);

        //Test the chosen board size
        //Toast.makeText(GamePlayActivity.this,"Test : "+menuController.getPlayer2_color(),Toast.LENGTH_SHORT).show();

        /******************************
         * Initialize game logic stuff
         ******************************/
        mColumns = menuController.getBoard_column();
        mRows = menuController.getBoard_row();
        mTops = new int[mColumns];
        mBoard = new int[mColumns][mRows];
        currentRound = menuController.getNum_rounds();
        mGamePlayView = (View) findViewById(R.id.gameViewRelative);
        //mController = new GamePlayController(this, this, (GamePlayView) mGamePlayView);

        Log.wtf("GamePlayActivity","before adjust");
       //mGamePlayView.adjustSize(mColumns, mRows, mController);
        Log.wtf("GamePlayActivity", "after adjust");

    }

    public int doMove(int column)
    {
        Log.wtf("GamePlayActivity", "doing a move");
        //mGamePlayView.animateDiskPlacement(mTops[column], column, R.drawable.gridblank );
        return 0;
    }
}
