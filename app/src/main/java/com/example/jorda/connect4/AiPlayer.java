package com.example.jorda.connect4;

/**
 * Created by jordan on 3/2/2018.
 */

public class AiPlayer extends Player {
    Board thinkingBoard;

    AiPlayer(Board board_in, boolean PlayerType)
    {
        mPlayerType = PlayerType;
        thinkingBoard = new Board(board_in);
    }

    int getMove() {
        return thinkingBoard.alphabeta(5, mPlayerType);
    }


}
