package com.TeamAwesome.ConnectFour;

import com.TeamAwesome.ConnectFour.ai.AiPlayer2;
import com.TeamAwesome.ConnectFour.board.BoardLogic2;

import org.junit.Assert;
import org.junit.Test;

public class AiMoveTest2 {
    private final int[] free2 = {5,6,5,6,6,7,7,7};

    private final int[][] grid2 = {
            {0,0,0,0,0,0,0,0,},
            {0,0,0,0,0,0,0,0,},
            {0,0,0,0,0,0,0,0,},
            {0,0,0,0,0,0,0,0,},
            {0,0,0,0,0,0,0,0,},
            {2,0,1,0,0,0,0,0,},
            {2,2,1,1,1,0,0,0,},
    };

    @Test
    public void testAiMove(){
        BoardLogic2 boardLogic2 =new BoardLogic2(grid2,free2);
        AiPlayer2 aiPlayer2 = new AiPlayer2(boardLogic2);
        aiPlayer2.setDifficulty(10);
        int columExpected = 5;
        Assert.assertEquals(columExpected,aiPlayer2.getColumn());
    }
}
