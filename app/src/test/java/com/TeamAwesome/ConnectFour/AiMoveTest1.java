package com.TeamAwesome.ConnectFour;

import com.TeamAwesome.ConnectFour.ai.AiPlayer1;
import com.TeamAwesome.ConnectFour.board.BoardLogic1;

import org.junit.Assert;
import org.junit.Test;

public class AiMoveTest1 {
    private final int[] free1 = {4,5,4,5,5,6,6};

    private final int[][] grid1 = {
            {0,0,0,0,0,0,0,},
            {0,0,0,0,0,0,0,},
            {0,0,0,0,0,0,0,},
            {0,0,0,0,0,0,0,},
            {2,0,1,0,0,0,0,},
            {2,2,1,1,1,0,0,},
    };

    @Test
    public void testAiMove(){
        BoardLogic1 boardLogic1 =new BoardLogic1(grid1,free1);
        AiPlayer1 aiPlayer1 = new AiPlayer1(boardLogic1);
        aiPlayer1.setDifficulty(10);
        int columExpected = 5;
        Assert.assertEquals(columExpected,aiPlayer1.getColumn());
    }
}
