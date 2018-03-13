package com.example.jorda.connect4;

import android.app.AlertDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

/**
 * Created by school on 2/28/2018.
 */

public class GamePlayController implements View.OnClickListener {
    private Context mContext;

    public int COLS;
    public int ROWS;
    private int[][] mGrid;
    private int[] mFree;
    private int mPlayerTurn;

    private GamePlayModel mGamePlay;
    private GamePlayView mPlayView;

    public GamePlayController(Context context, GamePlayView PlayView_in, int cols, int rows)
    {
        mContext = context;
        this.COLS = cols;
        this.ROWS = rows;
        this.mContext = context;
        mGrid = new int[ROWS][COLS];
        mFree = new int[COLS];
        mGamePlay = new GamePlayModel(mGrid, mFree);
        mPlayerTurn = 1;
        // null the mGrid and mFree counter for every column
        for (int j = 0; j < COLS; ++j) {
            for (int i = 0; i < ROWS; ++i) {
                mGrid[i][j] = 0;
            }
            mFree[j] = ROWS;
        }

        //mGamePlay = GamePlay_in;
        mPlayView = PlayView_in;
        if (mPlayView != null) {
            mPlayView.initialize(this, COLS, ROWS);
        }
    }

    @Override
    public void onClick(@NonNull View v)
    {
        int column = (int)v.getX() / (int) mPlayView.getCellWidth();
        if (mFree[column] == 0) {
            return;
        }

        mGamePlay.placeMove(column, mPlayerTurn);
        //mPlayView.dropDisc(mFree[column], column, mPlayerTurn);
        mPlayView.dropDisc(ROWS - mGamePlay.tops(column), column, mPlayerTurn);
        Log.wtf("GamePlayController", "column is "+column+" mFree is "+mFree[column]+" ROWS is "+ROWS+" tops is "+mGamePlay.tops(column));
        mPlayerTurn = mPlayerTurn == 1 ? 2 : 1;

        //mGamePlay.doMove( (int)v.getX() / (int) mPlayView.getCellWidth() );
    }
}
