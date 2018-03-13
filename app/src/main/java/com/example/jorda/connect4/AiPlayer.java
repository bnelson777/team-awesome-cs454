package com.example.jorda.connect4;

/**
 * Created by jordan on 3/2/2018.
 */

public class AiPlayer extends Player {
    Board thinkingBoard;
    private int mDifficulty;

    AiPlayer(boolean PlayerType, Board board_in, int piece, int winPiece, int difficulty)
    {
        super(PlayerType, piece, winPiece);
        maximizingPlayer = PlayerType;
        thinkingBoard = new Board(board_in);
        mDifficulty = difficulty;
        type = "AI";
    }

    int getMove() {
        return thinkingBoard.alphabeta(mDifficulty, maximizingPlayer);
    }


}
