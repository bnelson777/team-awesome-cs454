package com.TeamAwesome.ConnectFour;

import com.TeamAwesome.ConnectFour.ai.AiPlayer1;
import com.TeamAwesome.ConnectFour.board.BoardLogic3;

import org.junit.Assert;
import org.junit.Test;

public class AiMoveTest3 {
    private final int[] free3 = {6,7,6,7,7,8,8,8,8,8};

    private final int[][] grid3 = {
            {0,0,0,0,0,0,0,0,0,0,},
            {0,0,0,0,0,0,0,0,0,0,},
            {0,0,0,0,0,0,0,0,0,0,},
            {0,0,0,0,0,0,0,0,0,0,},
            {0,0,0,0,0,0,0,0,0,0,},
            {0,0,0,0,0,0,0,0,0,0,},
            {2,0,1,0,0,0,0,0,0,0,},
            {2,2,1,1,1,0,0,0,0,0,},
    };

    @Test
    public void testAiMove(){
        BoardLogic3 boardLogic3 =new BoardLogic3(grid3,free3);
        AiPlayer1 aiPlayer1 = new AiPlayer1(boardLogic3);
        aiPlayer1.setDifficulty(10);
        int columExpected = 5;
        Assert.assertEquals(columExpected,aiPlayer1.getColumn());
    }
}
