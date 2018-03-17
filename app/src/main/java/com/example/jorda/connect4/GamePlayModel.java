package com.example.jorda.connect4;

import android.support.annotation.NonNull;
import android.util.Log;

/**
 * Created by jordan on 3/5/2018.
 */

public class GamePlayModel {
    Board mBoard;
    boolean currentPlayer;
    int player1_score;
    int player2_score;
    int current_round;
    int total_rounds;


    public GamePlayModel(int width, int height, int rounds) {
        mBoard = new Board(width, height);
        currentPlayer = true;
        player1_score = player2_score = 0;
        current_round = 1;
        total_rounds = rounds;
    }

    public boolean placeMove(int column) {
        if (mBoard.makeMove(column, currentPlayer) < 0)
            return false;
        currentPlayer = !currentPlayer;
        return true;
    }

    public int tops(int column) {
        return mBoard.getTop(column);
    }

    // Return # of free spaces in column
    public int free(int column) {
        return mBoard.free(column);
    }

    public boolean getCurrentPlayer() {
        return currentPlayer;
    }

    public int getPlayer1_score() {
        return player1_score;
    }

    public int getPlayer2_score()
    {
        return player2_score;
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
    public int getCurrent_round() {return current_round;}
    public int getTotal_rounds() {return total_rounds;}
}
