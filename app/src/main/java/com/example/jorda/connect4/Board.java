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
        boardFull = false;
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

    public void reset()
    {
        grid = new int[columns][rows];
        tops = new int[columns];
        heuristicScore = 0;
        maxWon = false;
        minWon = false;
        winX = new int[4];
        winY = new int[4];
        boardFull = false;
    }

    public Board simulateMove(int column, boolean piece)
    {
        Board b = new Board(this);
        b.makeMove(column,piece);
        return b;
    }

    public int makeMove(int column, boolean piece)
    {
        //Log.wtf("makeMove", " checking: "+column+" "+columns+" "+rows+" "+" "+boardFull+" "+maxWon+" "+minWon);
        //Log.wtf("makeMove", "tops: "+tops[0]+tops[1]+tops[2]+tops[3]+tops[4]+tops[5]+tops[6]);
        if (column < 0 || column >= columns || tops[column] == rows
                || boardFull || maxWon || minWon)
            return -1;
        //Log.wtf("Board","moving to "+column+" "+tops[column]);
        grid[column][tops[column]] = (piece?first_player:second_player);
        tops[column]++;

        // Check if the board is full (ie, a stalemate)
        boardFull = true;
        //Log.wtf("makeMove", "rows: "+rows+" full: "+boardFull+"bf: "+tops[0]+tops[1]+tops[2]+tops[3]+tops[4]+tops[5]+tops[6]);
        for (int i=0;i<tops.length;i++)
            boardFull = boardFull && (tops[i] == rows);
        //Log.wtf("makeMove", "rows: "+rows+" full: "+boardFull+" af: "+tops[0]+tops[1]+tops[2]+tops[3]+tops[4]+tops[5]+tops[6]);

        // Check for a win, and
        // adjust heuristic score (Max-player potential 4-in-rows
        // minus potential Min-player 4-in-rows)
        for (int rowDir=-1; rowDir <= 1; rowDir++)
            for (int colDir=-1; colDir <=1; colDir++)
            {
                int r = tops[column] - 1;
                int c = column;
                if (!(rowDir == 0 && colDir == 0))
                {

                    /*
                     * This section checks if the piece placed was
                     * the last in a row of four - ie, it was
                     * on either end of the four. It is X where the winning
                     * row is XOOX
                     */
                    boolean tWon = true;
                    for (int i = 0; i < 4; i++) {
                        if (!(     r < rows && r >= 0 && c < columns && c >= 0
                                && c + i * colDir < columns && c + i * colDir >= 0
                                && r + i * rowDir < rows && r + i * rowDir >= 0
                                && ((piece ? first_player : second_player)
                                        == grid[c + i * colDir][r + i * rowDir])))
                        {
                            tWon = false;
                            /* Explanation of heuristic score:
                             * it is taken to be the total number of possible
                             * 4-in-rows we can make on the board.
                             *
                             * If we focus on one specific direction, the number
                             * of 4-in-rows a point X can be a part of are
                             * XOOO,  OXOO, OOOX, or OOXO.
                             *
                             * Thus, when we determine a point
                             * is not part of a winning 4-in-row in this direction,
                             * we subtract 4 potential lines from the total -
                             * where the total score is the number of potential
                             * 4-in-rows we can make on this board.
                             *
                             * But, if one of those 'potential' 4-in-rows
                             * overhangs the edge, it wasn't a potential anyway,
                             * so we don't subtract it. If we've hit an opponent
                             * piece or board edge at i distance away from X, we
                             * can subtract i possible 4-in-rows from the four
                             * potentials the point X could be in, in this given
                             * direction.
                             *
                             * Thus the overall score lowers by (4-i)
                             */
                            heuristicScore -= (4 - i);
                            break;
                        }
                    }

                    if (tWon == true) {
                        // populate winX[] and winY[]
                        for (int i = 0; i < 4; i++) {
                            winX[i] = c + i * colDir;
                            winY[i] = r + i * rowDir;
                        }

                        maxWon = piece;
                        minWon = !piece;
                        heuristicScore = (piece ? 1 : -1) * columns * rows;
                        return 0;
                    }


                    /*
                     * This section checks if the piece placed was
                     * in the middle of a row of four - ie, it was
                     * either X of OXXO
                     */
                    tWon = true;
                    r = (tops[column] - 1) - rowDir;
                    c = column - colDir;
                    //Log.wtf("Board", "Checking at " + c + " " + r + " in " + colDir + " " + rowDir);
                    for (int i = 0; i < 4; i++) {
                        //Log.wtf("wincheck: ", "piece: "+piece+" "+(c+i*colDir)+ " "+(r+i*rowDir) );
                        if (!(     r < rows && r >= 0 && c < columns && c >= 0
                                && c + i * colDir < columns && c + i * colDir >= 0
                                && r + i * rowDir < rows && r + i * rowDir >= 0
                                && ((piece ? first_player : second_player)
                                        == grid[c + i * colDir][r + i * rowDir])))
                        {
                            tWon = false;
                            heuristicScore -= (4 - i);
                            break;
                        }
                    }

                    if (tWon == true) {
                        // populate winX[] and winY[]
                        for (int i = 0; i < 4; i++) {
                            winX[i] = c + i * colDir;
                            winY[i] = r + i * rowDir;
                        }

                        maxWon = piece;
                        minWon = !piece;
                        heuristicScore = (piece ? 1 : -1) * columns * rows;
                        return 0;
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
