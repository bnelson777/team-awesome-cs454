package com.example.jorda.connect4;
import java.io.Serializable;
import android.util.Log;

/**
 * Created by branden on 3/12/18.
 */

public class Player implements Serializable{
    protected boolean maximizingPlayer;
    private int mPieceResource;
    private int mWinPieceResource;
    protected String type;


    private String name;
    private int pNumber;


    public Player(int pNumber){
        this.pNumber = pNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getpNumber() {
        return pNumber;
    }


    public String getName() {
        return name;
    }


    Player( boolean maxPlayer, int piece_resource, int win_piece_resource, String name_in)
    {
        maximizingPlayer = maxPlayer;
        Log.wtf("Player saving",""+piece_resource+" "+"win_piece_resource");
        mPieceResource = piece_resource;
        mWinPieceResource = win_piece_resource;
        type = "Human";
        name = name_in;
    }



    public int getMove(Board in)
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

    public boolean isAi() {
        //Log.wtf("player","my type "+type);
        return type.equals("AI");
    }
}
