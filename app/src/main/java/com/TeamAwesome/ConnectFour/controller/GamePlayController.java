package com.TeamAwesome.ConnectFour.controller;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.TeamAwesome.ConnectFour.BuildConfig;
import com.TeamAwesome.ConnectFour.activity.GamePlayActivity;
import com.TeamAwesome.ConnectFour.ai.AiPlayer;
import com.TeamAwesome.ConnectFour.board.BoardLogic;
import com.TeamAwesome.ConnectFour.board.BoardLogic.Outcome;
import com.TeamAwesome.ConnectFour.rules.GameRules;
import com.TeamAwesome.ConnectFour.rules.Player;
import com.TeamAwesome.ConnectFour.utils.BoardType;
import com.TeamAwesome.ConnectFour.utils.Constants;
import com.TeamAwesome.ConnectFour.view.BoardView;

import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class GamePlayController implements View.OnClickListener {

    public int getNumberOfRounds() {
        return NumberOfRounds;
    }

    public void setNumberOfRounds(int numberOfRounds) {
        NumberOfRounds = numberOfRounds;
    }

    private int NumberOfRounds=0; //Added by Soyoung

    private static final String TAG = GamePlayController.class.getName();
    /**
     * number of columns and rows
     */
    public int COLS;
    public int ROWS;
    private BoardType boardType;

    /**
     * mGrid, contains 0 for empty cell or player ID
     */
    private int[][] mGrid;

    /**
     * mFree cells in every column
     */
    private int[] mFree;

    /**
     * board mBoardLogic (winning check)
     */
    private BoardLogic mBoardLogic;

    /**
     * Instance of Ai player
     */
    @Nullable
    private AiPlayer mAiPlayer;

    /**
     * current status
     */
    @NonNull
    private Outcome mOutcome = Outcome.NOTHING;

    /**
     * if the game is mFinished
     */
    private boolean mFinished = true;

    /**
     * player turn
     */
    private int mPlayerTurn;

    private final Context mContext;

    private final BoardView mBoardView;

    private final String mP1name;

    /**
     * Game rules
     */
    @NonNull
    private final GameRules mGameRules;

    private boolean mAiTurn;

    public GamePlayController(Context context, BoardView boardView, @NonNull GameRules mGameRules, BoardType boardType_in, int cols, int rows, String p1name) {
        this.COLS = cols;
        this.ROWS = rows;
        this.boardType = boardType_in;
        this.mContext = context;
        this.mGameRules = mGameRules;
        this.mBoardView = boardView;
        this.mP1name = p1name;
        mGrid = new int[ROWS][COLS];
        mFree = new int[COLS];
        mBoardLogic = new BoardLogic(mGrid, mFree);
        initialize();
        if (mBoardView != null) {
            mBoardView.initialize(this, mGameRules, boardType, COLS, ROWS, p1name);
        }
    }

    /**
     * initialize game board with default values and player turn
     */
    private void initialize() {
        mPlayerTurn = mGameRules.getRule(GameRules.FIRST_TURN);
        // unfinished the game
        mFinished = false;
        mOutcome = Outcome.NOTHING;
        // null the mGrid and mFree counter for every column
        for (int j = 0; j < COLS; ++j) {
            for (int i = 0; i < ROWS; ++i) {
                mGrid[i][j] = 0;
            }
            mFree[j] = ROWS;
        }

        // create AI if needed
        if (mGameRules.getRule(GameRules.OPPONENT) == GameRules.Opponent.AI) {
            mAiPlayer = new AiPlayer(mBoardLogic);
            switch (mGameRules.getRule(GameRules.LEVEL)) {
                case GameRules.Level.EASY:
                    mAiPlayer.setDifficulty(5);
                    break;
                case GameRules.Level.NORMAL:
                    mAiPlayer.setDifficulty(7);
                    break;
                case GameRules.Level.HARD:
                    mAiPlayer.setDifficulty(10);
                    break;
                default:
                    mAiPlayer = null;
                    break;
            }
        } else {
            mAiPlayer = null;
        }

        // if it is a computer turn, go ahead with it
        if (mPlayerTurn == GameRules.FirstTurn.PLAYER2 && mAiPlayer != null) aiTurn();
    }

    /**
     * ai turn goes here
     */
    private void aiTurn() {

        if (mFinished) return;
        new AiTask().execute();
    }


    /**
     * drop disc into a column
     *
     * @param column column to drop disc
     */
    private void selectColumn(int column) {
        if (mFree[column] == 0) {
            if (BuildConfig.DEBUG) {
                Log.e(TAG, "full column or game is mFinished");
            }
            return;
        }

        mBoardLogic.placeMove(column, mPlayerTurn);

        // put disc
        mBoardView.dropDisc(mFree[column], column, mPlayerTurn);

        // switch player
        mPlayerTurn = mPlayerTurn == Player.PLAYER1
                ? Player.PLAYER2 : Player.PLAYER1;

        // check if someone has won
        checkForWin();
        //   board.displayBoard();
        mAiTurn = false;
        if (BuildConfig.DEBUG) {
            mBoardLogic.displayBoard();
            Log.e(TAG, "Turn: " + mPlayerTurn);
        }
        // AI move if needed
        if (mPlayerTurn == Player.PLAYER2 && mAiPlayer != null) aiTurn();
    }

    /**
     * execute board mBoardLogic for win check and update ui
     */
    private void checkForWin() {
        mOutcome = mBoardLogic.checkWin();

        if (mOutcome != Outcome.NOTHING) {
            mFinished = true;
            ArrayList<ImageView> winDiscs = mBoardLogic.getWinDiscs(mBoardView.getCells());
            if(NumberOfRounds==1){
                mBoardView.setFinished(true);
            }
            mBoardView.showWinStatus(mOutcome, winDiscs);

            final Handler handler = new Handler(); //added by soyoung
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Do something after 5s = 5000ms
                    if(NumberOfRounds>1) {
                        restartGame();
                        NumberOfRounds--;
                    }
                }
            }, 2000);

        } else {
            mBoardView.togglePlayer(mPlayerTurn);
        }
    }

    public void exitGame()
    {
            ((GamePlayActivity) mContext).finish();
    }

    /**
     * restart game by resetting values and UI
     */
    public void restartGame() {
        initialize();
        mBoardView.resetBoard();
        if (BuildConfig.DEBUG) {
            Log.e(TAG, "Game restarted");
        }
    }


    @Override
    public void onClick(@NonNull View view) {
        if (mFinished || mAiTurn) return;
        int col = mBoardView.colAtX(view.getX());
        if (BuildConfig.DEBUG) {
            Log.e(TAG, "Selected column: " + col);
        }
        selectColumn(col);
    }

    /**
     * run ai movement in background thread
     */
    class AiTask extends AsyncTask<Void, Void, Integer> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mAiTurn = true;
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            try {
                Thread.currentThread();
                sleep(Constants.AI_DELAY);
            } catch (InterruptedException e) {
                if (BuildConfig.DEBUG) {
                    e.printStackTrace();
                }
            }
            return mAiPlayer.getColumn();
        }

        @Override
        protected void onPostExecute(Integer integer) {
            selectColumn(integer);
        }
    }
}
