package com.example.jorda.connect4;

import android.support.annotation.NonNull;
import android.util.Log;

/**
 * Created by jordan on 3/5/2018.
 */

public class GamePlayModel {
    @NonNull
    private final int[][] mGrid;
    public final int numCols;
    private final int numRows;
    private final int[] mFree;
    Board mBoard;
    boolean currentPlayer;

    /**
     * Initialise members
     *
     * @param grid reference to board grid
     * @param free reference to column height
     */
    public GamePlayModel(@NonNull int[][] grid, int[] free) {
        mGrid = grid;
        numRows = grid.length;
        numCols = grid[0].length;
        this.mFree = free;

        mBoard = new Board(grid[0].length, grid.length);
        currentPlayer = true;
    }

    /**
     * placing a Move on the mGrid
     */
    public boolean placeMove(int column, int player) {
        if (mFree[column] > 0) {
            mGrid[mFree[column] - 1][column] = player;
            mFree[column]--;
        }
        //Log.wtf("GamePlayModel","calling makeMove");
        if (-1 == mBoard.makeMove(column, currentPlayer))
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
}
