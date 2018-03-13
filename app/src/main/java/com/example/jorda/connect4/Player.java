package com.example.jorda.connect4;

import android.util.Log;

/**
 * Created by jordan on 3/2/2018.
 */

public class Player {
    boolean maximizingPlayer;
    int mPieceResource;
    int mWinPieceResource;
    String type;

    Player()
    {

    }

    Player( boolean maxPlayer, int piece_resource, int win_piece_resource)
    {
        maximizingPlayer = maxPlayer;
        Log.wtf("Player saving",""+piece_resource+" "+"win_piece_resource");
        mPieceResource = piece_resource;
        mWinPieceResource = win_piece_resource;
        type = "Human";
    }



    int getMove(Board in)
    {
        return -1;
    }

    public int piece()
    {
        return mPieceResource;
    }

    public int winPiece()
    {
        return mWinPieceResource;
    }

    public boolean isAi() { Log.wtf("player","my type "+type); return type.equals("AI");}
}
