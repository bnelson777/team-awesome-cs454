package com.example.jorda.connect4;

import android.support.annotation.NonNull;
import android.util.Log;

/**
 * Created by jordan on 3/5/2018.
 */

public class GamePlayModel {
    Board mBoard;
    boolean currentPlayer;


    public GamePlayModel(int width, int height)
    {
        mBoard = new Board(width, height);
        currentPlayer = true;
    }

    public boolean placeMove(int column) {
        if ( mBoard.makeMove(column, currentPlayer) < 0)
            return false;
        currentPlayer = !currentPlayer;
        return true;
    }

    public int tops(int column)
    {
        return mBoard.getTop(column);
    }

    // Return # of free spaces in column
    public int free(int column)
    {
        return mBoard.free(column);
    }

    public boolean getCurrentPlayer()
    {
        return currentPlayer;
    }

    public boolean maxWon()
    {
        return mBoard.maxWon;
    }
    public boolean minWon()
    {
        return mBoard.minWon;
    }
    public int winX(int i)
    {
        return mBoard.winX[i];
    }
    public int winY(int i)
    {
        return mBoard.winY[i];
    }
    public Board getBoard() {return mBoard;}
}
