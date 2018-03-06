package com.example.jorda.connect4;

import android.support.annotation.NonNull;

/**
 * Created by jordan on 3/5/2018.
 */

public class GamePlayModel {
    @NonNull
    private final int[][] mGrid;
    public final int numCols;
    private final int numRows;
    private final int[] mFree;

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
    }

    /**
     * placing a Move on the mGrid
     */
    public void placeMove(int column, int player) {
        if (mFree[column] > 0) {
            mGrid[mFree[column] - 1][column] = player;
            mFree[column]--;
        }
    }
}
