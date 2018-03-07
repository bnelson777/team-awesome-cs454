package com.example.jorda.connect4;

/**
 * Created by jordan on 3/2/2018.
 */

public class Player {
    boolean mPlayerType;

    Player()
    {

    }

    Player(boolean PlayerType)
    {
        mPlayerType = PlayerType;
    }

    int getMove(Board in)
    {
        return -1;
    }
}
