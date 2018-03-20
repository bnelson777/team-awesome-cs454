package com.example.jorda.connect4;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.TextView;import android.view.ViewGroup;import android.widget.ImageView;

/**
 * Created by school on 2/28/2018.
 */

public class GamePlayController implements View.OnClickListener {
    private Context mContext;

    private GamePlayModel mGamePlay;
    private GamePlayView mPlayView;
    boolean waitForAi;

    Player player1;
    Player player2;

    public GamePlayController(Context context, GamePlayView PlayView_in, MenuController menu) {
        mContext = context;
        mGamePlay = new GamePlayModel(menu.getBoard_column(), menu.getBoard_row(), menu.getNum_rounds());
        mPlayView = PlayView_in;

        // Get player types and colors from menu
        int p1_piece, p1_win_piece, p1_difficulty;
        int p2_piece, p2_win_piece, p2_difficulty;

        p1_piece = (menu.getPlayer1_color().equals("White") ?
                R.drawable.player_piece_white : R.drawable.player_piece_black);
        Log.wtf("controller","White? "+(menu.getPlayer1_color().equals("White") ? "w" : "b"));

        p1_win_piece = menu.getPlayer1_color().equals("White") ?
                R.drawable.player_piece_win_white : R.drawable.player_piece_win_black;

        p2_piece = menu.getPlayer2_color().equals("White") ?
                R.drawable.player_piece_white : R.drawable.player_piece_black;

        p2_win_piece = menu.getPlayer2_color().equals("White") ?
                R.drawable.player_piece_win_white : R.drawable.player_piece_win_black;

        if (mPlayView != null) {
            mPlayView.initialize(this, menu.getBoard_column(), menu.getBoard_row(), p1_piece, p1_win_piece, p2_piece, p2_win_piece);
            mPlayView.showRounds(mGamePlay.getCurrent_round(), mGamePlay.getTotal_rounds());
        }

        p1_difficulty = menu.getPlayer1_difficulty().equals("Easy") ? 3 :
                menu.getPlayer1_difficulty().equals("Medium") ? 5 : 7;

        p2_difficulty = menu.getPlayer2_difficulty().equals("Easy") ? 3 :
                menu.getPlayer1_difficulty().equals("Medium") ? 5 : 7;

        if (menu.getPlayer1().equals("Network")) {
            waitForAi = false;
        } else if (menu.getPlayer1().equals("Robot overlord"))
        {
            Log.wtf("game controller","setting p1 to AI");
            player1 = new AiPlayer(true,mGamePlay.getBoard(), p1_piece, p1_win_piece, "testname", p1_difficulty);
            waitForAi = true;
        }
        else { //local human
            Log.wtf("gpcont","human player");
            Log.wtf("gp cont" ,""+p1_piece+" "+p1_win_piece+" "+R.drawable.player_piece_white+" "+R.drawable.player_piece_win_white);
            player1 = new Player(true, p1_piece, p1_win_piece, "testname");
            waitForAi = false;
        }

        if (menu.getPlayer2().equals("Network")) {

        } else if (menu.getPlayer2().equals("Robot overlord"))
        {
            Log.wtf("game controller","setting p2 to AI");
            player2 = new AiPlayer(true, mGamePlay.getBoard(), p2_piece, p2_win_piece, "testname", p2_difficulty);
        }
        else { // local human
            player2 = new Player(true, p2_piece, p2_win_piece, "testname");
        }

        Log.wtf("game controller","checking for AI");
        if (mGamePlay.getCurrentPlayer() ? player1.isAi() : player2.isAi()) {
            Log.wtf("game controller","AI found");
            waitForAi = true;
            int nextplayermove = mGamePlay.getCurrentPlayer() ? player1.getMove(mGamePlay.getBoard()) : player2.getMove(mGamePlay.getBoard());
            waitForAi = false;
            doMove(nextplayermove);
        }


        String playerName1 = "John";
        String playerName2 = "Jacob";
        ((TextView) mPlayView.findViewById(R.id.player1_name)).setText(playerName1);
        //Log.wtf("controller:","player1name: "+R.id.player1_name);
        ((TextView) mPlayView.findViewById(R.id.player1_score)).setText(Integer.toString(mGamePlay.getPlayer1_score()));
        ((TextView) mPlayView.findViewById(R.id.player2_name)).setText(playerName2);
        //Log.wtf("controller:","player2name: "+R.id.player2_name);
        ((TextView) mPlayView.findViewById(R.id.player2_score)).setText(Integer.toString(mGamePlay.getPlayer2_score()));
    }

    private void doMove(int column)
    {
        Log.wtf("doMove", ""+mGamePlay.getCurrentPlayer() );
        if (mGamePlay.placeMove(column))
            mPlayView.dropDisc(mGamePlay.free(column), column, mGamePlay.getCurrentPlayer() ? player2.piece() : player1.piece());
        Log.wtf("doMove", ""+mGamePlay.getCurrentPlayer() );

        mPlayView.highlightPlayer(mGamePlay.getCurrentPlayer() ? 1 : 2);
        mPlayView.unhighlightPlayer(mGamePlay.getCurrentPlayer() ? 2 : 1);
        mPlayView.showRounds(mGamePlay.getCurrent_round(), mGamePlay.getTotal_rounds());

        if (mGamePlay.maxWon() || mGamePlay.minWon())
        {
            for (int i=0; i<4; i++) {
                mPlayView.highlight(mGamePlay.winX(i), mGamePlay.winY(i),
                        (mGamePlay.maxWon() ? player1.winPiece() : player2.winPiece()));
            }
            if (mGamePlay.maxWon())
                mPlayView.winMessage("Player1 wins!");
            if (mGamePlay.minWon())
                mPlayView.winMessage("Player 2 wins!");
            return; // don't execute ai move if game has ended
        }

        if (mGamePlay.getCurrentPlayer() ? player1.isAi() : player2.isAi()) {
            Log.wtf("GamePlayController",""+mGamePlay.getCurrentPlayer() + " " + player1.isAi() + " " + player2.isAi());
            waitForAi = true;
            Log.wtf("gameplaycontroller", "getting Ai move");
            int nextplayermove = mGamePlay.getCurrentPlayer() ? player1.getMove(mGamePlay.getBoard()) : player2.getMove(mGamePlay.getBoard());
            Log.wtf("gameplaycontroller", ""+mGamePlay.getCurrentPlayer());
            Log.wtf("gameplaycontroller", "got ai move");
            waitForAi = false;
            doMove(nextplayermove);
        }
    }

    @Override
    public void onClick(@NonNull View v)
    {
        if (waitForAi)
            return;

        // if human, get column they clicked and playa  move there
        int column = (int)v.getX() / (int) mPlayView.getCellWidth();
        doMove(column);
    }
}
