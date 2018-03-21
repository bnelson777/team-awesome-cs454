package com.example.jorda.connect4;

import android.content.Context;
import android.os.Handler;
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
    private MenuController mMenu;
    boolean waitForAi;

    Player player1;
    Player player2;
    private String player1_name;
    int row1;
    int column1;
    int Num_rounds;

    public GamePlayController(Context context, GamePlayView PlayView_in, MenuController menu, String Player_name) {
        mContext = context;
        mPlayView = PlayView_in;
        mMenu = menu;
        player1_name = Player_name;
        row1=menu.getBoard_row();
        column1=menu.getBoard_column();
        Num_rounds=menu.getNum_rounds();
        initialize();
    }

    private void initialize(){
        mGamePlay = new GamePlayModel( column1, row1, Num_rounds);
        // Get player types and colors from menu
        int p1_piece, p1_win_piece, p1_difficulty;
        int p2_piece, p2_win_piece, p2_difficulty;

        p1_piece = (mMenu.getPlayer1_color().equals("White") ?
                R.drawable.player_piece_white : R.drawable.player_piece_black);
        Log.wtf("controller","Came in!!!!!!!!!!!!!!!!!!!!!!!!!!");

        p1_win_piece = mMenu.getPlayer1_color().equals("White") ?
                R.drawable.player_piece_win_white : R.drawable.player_piece_win_black;

        p2_piece = mMenu.getPlayer2_color().equals("White") ?
                R.drawable.player_piece_white : R.drawable.player_piece_black;

        p2_win_piece = mMenu.getPlayer2_color().equals("White") ?
                R.drawable.player_piece_win_white : R.drawable.player_piece_win_black;

        if (mPlayView != null) {
            Log.wtf("controller","calling initialize");
            mPlayView.initialize(this, mMenu.getBoard_column(), mMenu.getBoard_row(), p1_piece, p1_win_piece, p2_piece, p2_win_piece);
            mPlayView.showRounds(mGamePlay.getCurrent_round(), mGamePlay.getTotal_rounds());
        }

        p1_difficulty = mMenu.getPlayer1_difficulty().equals("Easy") ? 3 :
                mMenu.getPlayer1_difficulty().equals("Medium") ? 5 : 7;

        p2_difficulty = mMenu.getPlayer2_difficulty().equals("Easy") ? 3 :
                mMenu.getPlayer1_difficulty().equals("Medium") ? 5 : 7;

        if (mMenu.getPlayer1().equals("Network")) {
            waitForAi = false;
        } else if (mMenu.getPlayer1().equals("Robot overlord"))
        {
            Log.wtf("game controller","setting p1 to AI");
            player1 = new AiPlayer(true,mGamePlay.getBoard(), p1_piece, p1_win_piece, "testname", p1_difficulty);
            waitForAi = true;
        }
        else { //local human
            Log.wtf("gpcont","human player");
            //Log.wtf("gp cont" ,""+p1_piece+" "+p1_win_piece+" "+R.drawable.player_piece_white+" "+R.drawable.player_piece_win_white);
            player1 = new Player(true, p1_piece, p1_win_piece, "testname");
            waitForAi = false;
        }

        if (mMenu.getPlayer2().equals("Network")) {

        } else if (mMenu.getPlayer2().equals("Robot overlord"))
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


        //String playerName1 = "John";
        String playerName2 = "Player2";
        ((TextView) mPlayView.findViewById(R.id.player1_name)).setText(player1_name);
        //Log.wtf("controller:","player1name: "+R.id.player1_name);
        ((TextView) mPlayView.findViewById(R.id.player1_score)).setText(Integer.toString(mGamePlay.getPlayer1_score()));
        ((TextView) mPlayView.findViewById(R.id.player2_name)).setText(playerName2);
        //Log.wtf("controller:","player2name: "+R.id.player2_name);
        ((TextView) mPlayView.findViewById(R.id.player2_score)).setText(Integer.toString(mGamePlay.getPlayer2_score()));
    }

    private void doMove(int column)
    {
        //Log.wtf("doMove", ""+(mGamePlay.getCurrentPlayer() ? player1.piece() : player2.piece()));
        if (mGamePlay.placeMove(column))
            mPlayView.dropDisc(mGamePlay.free(column), column, mGamePlay.getCurrentPlayer() ? player2.piece() : player1.piece());

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
                mPlayView.winMessage(player1_name+" wins!");
            if (mGamePlay.minWon())
                mPlayView.winMessage("Player 2 wins!");



            final Handler handler = new Handler(); //added by soyoung

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Do something after 5s = 5000ms
                    //mPlayView.resetDiscs(row1, column1);
                    mPlayView.winMessage("");
                    initialize();
                }
            }, 3000);

        }

        if (mGamePlay.getCurrentPlayer() ? player1.isAi() : player2.isAi()) {
            waitForAi = true;
            int nextplayermove = mGamePlay.getCurrentPlayer() ? player1.getMove(mGamePlay.getBoard()) : player2.getMove(mGamePlay.getBoard());
            waitForAi = false;
            doMove(nextplayermove);
        }
    }

    @Override
    public void onClick(@NonNull View v)
    {
        if (waitForAi)
            return;
        Log.wtf("game controller","onClick");

        // if human, get column they clicked and playa  move there

        int column = (int)v.getX() / (int) mPlayView.getCellWidth();
        Log.wtf("getCellWidth", mPlayView.getCellWidth()+" ");
        Log.wtf("column", column+" ");
        doMove(column);
    }
}
