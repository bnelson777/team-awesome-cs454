package com.TeamAwesome.ConnectFour;

import android.support.annotation.NonNull;

import com.TeamAwesome.ConnectFour.board.BoardLogic1;

import org.junit.Assert;
import org.junit.Test;

public class WinCheckTest1 {
    @NonNull
    private int[][] gridForHorizontalCheck = {
            {0,0,0,0,0,0,0,},
            {0,0,0,0,0,0,0,},
            {0,0,0,0,0,0,0,},
            {0,0,0,0,0,0,0,},
            {0,0,0,0,0,0,0,},
            {1,1,1,1,0,0,0,},
    };
    @NonNull
    private int[][] gridForVerticalCheck = {
            {0,0,0,0,0,0,0,},
            {0,0,0,0,0,0,0,},
            {1,0,0,0,0,0,0,},
            {1,0,0,0,0,0,0,},
            {1,0,0,0,0,0,0,},
            {1,0,0,0,0,0,0,},
    };
    @NonNull
    private int[][] gridForAcendingDiagonalCheck = {
            {0,0,0,0,0,0,0,},
            {0,0,0,0,0,0,0,},
            {0,0,0,1,0,0,0,},
            {0,0,1,2,0,0,0,},
            {0,1,2,2,0,0,0,},
            {1,2,2,2,0,0,0,},
    };
    private final int[][] gridFoDescendingDiagonalCheck = {
            {0,0,0,0,0,0,0,},
            {0,0,0,0,0,0,0,},
            {1,0,0,0,0,0,0,},
            {2,1,0,0,0,0,0,},
            {2,2,1,0,0,0,0,},
            {2,2,2,1,0,0,0,},
    };
    @Test
    public void testWInnerCheck(){
        BoardLogic1 boardLogic = new BoardLogic1(gridForVerticalCheck,null);
        Assert.assertSame("Winner check failed", BoardLogic1.Outcome.PLAYER1_WINS,boardLogic.checkWin());
    }
}
