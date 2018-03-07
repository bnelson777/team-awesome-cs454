package com.example.jorda.connect4;

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

    public Board(int width, int height) {
        columns = width;
        rows = height;
        grid = new int[columns][rows];
        tops = new int[columns];

        // Track heuristic score, so we can adjust it on each move,
        // Rather than recalculate the whole thing
        heuristicScore = 0;
        maxWon = false;
        minWon = false;
    }

    public Board(Board in) {
        this.minWon = in.minWon;
        this.maxWon = in.maxWon;
        this.heuristicScore = in.heuristicScore;

        this.columns = in.columns;
        this.rows = in.rows;
        this.tops = in.tops.clone();
        this.grid = new int[in.grid.length][];
        for (int i = 0; i < in.grid.length; i++)
            this.grid[i] = in.grid[i].clone();
    }

    public Board simulateMove(int column, int piece)
    {
        Board b = new Board(this);
        b.makeMove(column,piece);
        return b;
    }

    public int makeMove(int column, int piece)
    {
        if (column < 0 || column >= columns || tops[column] == rows)
            return -1;
        grid[column][tops[column]] = piece;
        tops[column]++;

        // adjust heuristic score (max potential 4-in-rows
        // minus potential min 4-in-rows)
        for (int rowDir=-1; rowDir <= 1; rowDir++)
            for (int colDir=-1; colDir <=1; colDir++)
                if (!(rowDir==0 && colDir==0)) {
                    boolean tWon = true;
                    int r = tops[column] - 1;
                    int c = column;
                    for (int i = 0; i < 4; i++)
                        if (r < rows && r >= 0 && c < columns && c >= 0)
                            if (piece != grid[c+i*colDir][r+i*colDir]) {
                                tWon = false;
                                heuristicScore -= (4 - i);
                                break;
                            }

                    if (tWon == true) {
                        if (piece == 1)
                            maxWon = true;
                        if (piece == -1)
                            minWon = true;
                    }
                }

        if (piece == -1) return -1*columns*rows;
        if (piece == 1)  return columns*rows;
        return heuristicScore;
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
                v = max(v, alphabeta(node.simulateMove(i, (maximizingPlayer ? 1 : -1)), depth - 1, alpha, beta, false));
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
                v = min(v, alphabeta(node.simulateMove(i, (maximizingPlayer ? 1 : -1)), depth - 1, alpha, beta, true));
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
        return maxWon == true || minWon == true;
    }
}
