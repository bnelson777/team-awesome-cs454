package com.example.jorda.connect4;

import android.util.Log;

/**
 * Created by jordan on 3/2/2018.
 */

public class AiPlayer extends Player {
    Board thinkingBoard;
    private int mDifficulty;

    AiPlayer(boolean PlayerType, Board board_in, int piece, int winPiece, String name_in, int difficulty)
    {
        super(PlayerType, piece, winPiece, name_in);
        maximizingPlayer = PlayerType;
        thinkingBoard = new Board(board_in);
        mDifficulty = difficulty;
        type = "AI";
    }

    @Override
    public int getMove(Board in) {
        /*
         * Create a virtual board for each possible move.
         * Run alphabeta on each virtual board, and save
         * the score values it gives for each board.
         * Then find the max of those scores.
         * Then return the move with the max score.
         */
        thinkingBoard = new Board(in);
        Board[] moveBoards = new Board[thinkingBoard.columns];
        int[] moves = new int[thinkingBoard.columns];
        boolean[] valid = new boolean[thinkingBoard.columns];

        for (int i = 0; i < moves.length; i++)
        {
            moveBoards[i] = new Board(thinkingBoard);
            valid[i] = (moveBoards[i].makeMove(i, maximizingPlayer) == 0);
            //Log.wtf("AiPlayer", "moving to "+i+" is valid: "+valid[i]);
        }

        for (int i=0; i<moves.length; i++) {
            if (!valid[i])
                continue;
            moves[i] = moveBoards[i].alphabeta(mDifficulty, maximizingPlayer);
            //Log.wtf("AiPlayer","score: "+i+moves[i]);
        }

        int column = 0;
        for (int i=0; i<moves.length; i++) {
            if (!valid[i])
                continue;
            if (moves[i] > moves[column] || !valid[column])
                column = i;
        }

        // Check if any of the moves are valid
        boolean flag = false;
        for (int i=0;i<thinkingBoard.columns; i++)
            flag = flag || valid[i];

        if (!flag) {
            return -1; // no valid moves
        } else {
            //Log.wtf("AiPlayer", "choosing " + column);
            return column;
        }
    }


}
