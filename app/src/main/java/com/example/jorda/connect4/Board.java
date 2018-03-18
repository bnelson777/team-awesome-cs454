package com.example.jorda.connect4;

import android.util.Log;

import static java.lang.Math.min;
import static java.lang.StrictMath.max;

/**
 * Created by jordan on 3/2/2018.
 */

public class Board {
    int columns, rows;
    int[][] grid;
    int[] tops; // Top of stack of each column

    int heuristicScore;
    boolean maxWon;
    boolean minWon;
    boolean boardFull;
    int[] winX;
    int[] winY;
    private static final int first_player = 1;
    private static final int second_player = -1;

    public Board(int width, int height) {
        columns = width;
        rows = height;
        grid = new int[columns][rows];
        tops = new int[columns];

        //Log.wtf("Board", "width"+width+" height"+height);
        //Log.wtf("Board","tops: "+tops[0]+tops[1]+tops[2]+tops[3]+tops[4]+tops[5]+tops[6]);

        // Track heuristic score, so we can adjust it on each move,
        // Rather than recalculate the whole thing
        heuristicScore = 0;
        maxWon = false;
        minWon = false;
        winX = new int[4];
        winY = new int[4];
    }

    public Board(Board in) {
        this.minWon = in.minWon;
        this.maxWon = in.maxWon;
        this.heuristicScore = in.heuristicScore;
        this.boardFull = in.boardFull;

        this.columns = in.columns;
        this.rows = in.rows;
        this.tops = in.tops.clone();
        this.grid = new int[in.grid.length][];
        for (int i = 0; i < in.grid.length; i++)
            this.grid[i] = in.grid[i].clone();
        this.winX = in.winX.clone();
        this.winY = in.winY.clone();
    }

    public Board simulateMove(int column, boolean piece)
    {
        Board b = new Board(this);
        b.makeMove(column,piece);
        return b;
    }

    public int makeMove(int column, boolean piece)
    {
        //Log.wtf("makeMove", " checking: "+column+" "+columns+" "+rows+" "+tops[column]+" "+boardFull+" "+maxWon+" "+minWon);
        //Log.wtf("makeMove", "tops: "+tops[0]+tops[1]+tops[2]+tops[3]+tops[4]+tops[5]+tops[6]);
        if (column < 0 || column >= columns || tops[column] == rows || boardFull || maxWon || minWon)
            return -1;
        //Log.wtf("Board","moving to "+column+" "+tops[column]);
        grid[column][tops[column]] = (piece?first_player:second_player);
        tops[column]++;


        boardFull = true;
        //Log.wtf("makeMove", "rows: "+rows+" full: "+boardFull+"bf: "+tops[0]+tops[1]+tops[2]+tops[3]+tops[4]+tops[5]+tops[6]);
        for (int i=0;i<tops.length;i++)
            boardFull &= (tops[i] == rows);
        //Log.wtf("makeMove", "rows: "+rows+" full: "+boardFull+" af: "+tops[0]+tops[1]+tops[2]+tops[3]+tops[4]+tops[5]+tops[6]);

        // adjust heuristic score (max potential 4-in-rows
        // minus potential min 4-in-rows)
        for (int rowDir=-1; rowDir <= 1; rowDir++)
            for (int colDir=-1; colDir <=1; colDir++)
                if (!(rowDir==0 && colDir==0)) {
                    boolean tWon = true;
                    int r = tops[column] - 1;
                    int c = column;
                    for (int i = 0; i < 4; i++) {
                        //Log.wtf("wincheck: ", "piece: "+piece+" "+(c+i*colDir)+ " "+(r+i*rowDir) );
                        if (r < rows && r >= 0 && c < columns && c >= 0
                                && c + i * colDir < columns && c + i * colDir >= 0
                                && r + i * rowDir < rows && r + i * rowDir >= 0) {
                            //Log.wtf("wincheck: ", "grid: "+grid[c + i * colDir][r + i * rowDir]);
                            if ((piece ? first_player : second_player) != grid[c + i * colDir][r + i * rowDir]) {
                                tWon = false;
                                heuristicScore -= (4 - i);
                                break;
                            }
                        } else {
                            tWon = false;
                            heuristicScore -= (4 - i);
                            break;
                        }
                    }

                    if (tWon == true) {
                        // populate winX[] and winY[]
                        for (int i=0; i<4; i++)
                        {
                            winX[i] = c+i*colDir;
                            winY[i] = r+i*rowDir;
                        }
                        if (piece) {
                            maxWon = true;
                            heuristicScore = columns*rows;
                        } else {
                            minWon = true;
                            heuristicScore = -1*columns*rows;
                        }
                    }
                }

        return 0; // return 0 for no errors
    }

    public int getHeuristicScore()
    {
        return heuristicScore;
    }

    int alphabeta(Board node, int depth, int alpha, int beta, boolean maximizingPlayer) {
        if (depth == 0 || node.isTerminal())
            return node.getHeuristicScore();
        if (maximizingPlayer) {
            int v = -1 * rows * columns; // effectively infinity, if our score is # of moves to win
            for (int i = 0; i < columns; i++) {
                v = max(v, alphabeta(node.simulateMove(i, maximizingPlayer ), depth - 1, alpha, beta, false));
                alpha = max(alpha, v);
                if (beta <= alpha)
                    break;
            }
            return v;
        }
        else
        {
            int v = rows * columns;
            for (int i=0; i<columns; i++) {
                v = min(v, alphabeta(node.simulateMove(i, maximizingPlayer), depth - 1, alpha, beta, true));
                beta = min(beta, v);
                if (beta <= alpha)
                    break;
            }
            return v;
         }
    }

    // Default values
    int alphabeta(int depth, boolean player)
    {
        return alphabeta(this, depth, -1*rows*columns, rows*columns, player);
    }

    // is this a terminal node?
    public boolean isTerminal()
    {
        return maxWon == true || minWon == true || boardFull == true;
    }

    /*
    Note that the top of a column is the FREE space above the topmost piece
     */
    public int getTop(int column)
    {
        //Log.wtf("gettop","column "+column+" top "+tops[column]);
        if (column < 0 || column > tops.length - 1)
            return -1;
        return tops[column];
    }

    public int free(int column)
    {
        if (column <0 || column>=columns)
            return -1;
        return rows - tops[column];
    }
}
