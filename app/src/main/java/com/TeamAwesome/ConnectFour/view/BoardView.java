package com.TeamAwesome.ConnectFour.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.TeamAwesome.ConnectFour.BuildConfig;
import com.TeamAwesome.ConnectFour.R;
import com.TeamAwesome.ConnectFour.board.BoardLogic;
import com.TeamAwesome.ConnectFour.controller.GamePlayController;
import com.TeamAwesome.ConnectFour.rules.GameRules;
import com.TeamAwesome.ConnectFour.rules.Player;
import com.TeamAwesome.ConnectFour.utils.BoardType;

import java.util.ArrayList;

/**
 * Created by jordan on 2/14/2018.
 */

public class BoardView extends RelativeLayout {
        private int COLS;
        private int ROWS;
        BoardType boardType;

        private static final String TAG = GamePlayController.class.getName();
        private GameRules mGameRules;
        private GamePlayController mListener;

        public boolean isFinished = false; //added by soyoung

        public boolean isFinished() {
            return isFinished;
        }

        public void setFinished(boolean finished) {
            isFinished = finished;
        }

        /**
         * view holder for player information
         */
        protected class PlayerInformation {
            @NonNull
            public final TextView name;
            @NonNull
            public final ImageView disc;
            public final View turnIndicator;
            public int score = 0; //added by soyoung

            public PlayerInformation(int player_name_id, int player_disc_id, int player_indicator_id) {
                name = (TextView) findViewById(player_name_id);
                disc = (ImageView) findViewById(player_disc_id);
                turnIndicator = findViewById(player_indicator_id);
            }
        }

        public PlayerInformation mPlayer1; //changed it to be public
        public PlayerInformation mPlayer2;

        /**
         * Array to hold all discs dropped
         */
        private ImageView[][] mCells;
        protected View mBoardView;
        protected TextView mWinnerView;
        protected Context mContext;

        public ImageView[][] getCells() {
            return mCells;
        }

        public BoardView(Context context) {
            super(context);
            init(context);
        }

        public BoardView(Context context, AttributeSet attrs) {
            super(context, attrs);
            init(context);
        }

        public BoardView(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            init(context);
        }

        private void init(Context context) {
            /*
            this.mContext = context;
            if (boardType == BoardType.BoardTypeOne)
                inflate(context, R.layout.game_board1, this);
            if (boardType == BoardType.BoardTypeTwo)
                inflate(context, R.layout.game_board2, this);
            if (boardType == BoardType.BoardTypeThree)
                inflate(context, R.layout.game_board3, this);
            mPlayer1 = new PlayerInformation(R.id.player1_name, R.id.player1_disc, R.id.player1_indicator);
            mPlayer2 = new PlayerInformation(R.id.player2_name, R.id.player2_disc, R.id.player2_indicator);
            if (boardType == BoardType.BoardTypeOne)
                mBoardView = findViewById(R.id.game_board1 );
            if (boardType == BoardType.BoardTypeTwo)
                mBoardView = findViewById(R.id.game_board2 );
            if (boardType == BoardType.BoardTypeThree)
                mBoardView = findViewById(R.id.game_board3 );
            mWinnerView = (TextView) findViewById(R.id.winner_text);
            */
        }

        public void initialize(GamePlayController gamePlayController, @NonNull GameRules gameRules, BoardType boardType_in, int cols_in, int rows_in) {
            this.COLS = cols_in;
            this.ROWS = rows_in;
            this.boardType = boardType_in;
            this.mGameRules = gameRules;
            this.mListener = gamePlayController;

            setPlayer1();
            setPlayer2();
            togglePlayer(gameRules.getRule(GameRules.FIRST_TURN));
            buildCells();
        }

        /**
         * initialize mPlayer1 information with Gameules
         */
        private void setPlayer1() {
            mPlayer1.disc.setImageResource(mGameRules.getRule(GameRules.DISC));
            mPlayer1.name.setText(mContext.getString(R.string.you));
        }

        /**
         * initialize mPlayer2 information with Gameules
         */
        private void setPlayer2() {
            mPlayer2.disc.setImageResource(mGameRules.getRule(GameRules.DISC2));
            mPlayer2.name.setText(mGameRules.getRule(GameRules.OPPONENT) == R.string.opponent_ai ?
                    mContext.getString(R.string.opponent_ai) : mContext.getString(R.string.opponent_player));
        }

        /**
         * build and clear board mCells
         */
        private void buildCells() {
            mCells = new ImageView[ROWS][COLS];
            for (int r = 0; r < ROWS; r++) {
                ViewGroup row = (ViewGroup) ((ViewGroup) mBoardView).getChildAt(r);
                row.setClipChildren(false);
                for (int c = 0; c < COLS; c++) {
                    ImageView imageView = (ImageView) row.getChildAt(c);
                    imageView.setImageResource(android.R.color.transparent);
                    imageView.setOnClickListener(mListener);
                    mCells[r][c] = imageView;
                }
            }
        }

        /**
         * Reset boar for new game
         */
        public void resetBoard() {
            //clear board mCells
            for (ImageView[] cell : mCells) {
                for (ImageView imageView : cell) {
                    imageView.setImageResource(android.R.color.transparent);
                }
            }
            togglePlayer(mGameRules.getRule(GameRules.FIRST_TURN));
            showWinStatus(BoardLogic.Outcome.NOTHING, null);
        }

        /**
         * Drop a disc of the current player at available row of selected column
         *
         * @param col column to drop disc
         * @param row row of the column
         */
        public void dropDisc(int row, int col, final int playerTurn) {
            final ImageView cell = mCells[row][col];
            float move = -(cell.getHeight() * row + cell.getHeight() + 15);
            cell.setY(move);
            cell.setImageResource(playerTurn == Player.PLAYER1 ?
                    mGameRules.getRule(GameRules.DISC) : mGameRules.getRule(GameRules.DISC2));
            cell.animate().translationY(0).setInterpolator(new BounceInterpolator()).start();
        }

        /**
         * get column from touch
         *
         * @param x touch location
         * @return column from  the location(0..6)
         */
        public int colAtX(float x) {
            float colWidth = mCells[0][0].getWidth();
            int col = (int) x / (int) colWidth;
            if (col < 0)
                return 0;
            //change
            if (col > COLS - 1 )
                return COLS - 1;
            return col;
        }

        /**
         * toggle player indicator
         *
         * @param playerTurn next players value
         */
        public void togglePlayer(int playerTurn) {
            mPlayer1.turnIndicator.setVisibility(playerTurn == Player.PLAYER1 ? VISIBLE : INVISIBLE);
            mPlayer2.turnIndicator.setVisibility(playerTurn == Player.PLAYER2 ? VISIBLE : INVISIBLE);
        }

        /**
         * Update UI with winning status
         *
         * @param outcome  winning status
         * @param winDiscs winning move discs
         */
        public void showWinStatus(@NonNull BoardLogic.Outcome outcome, @NonNull ArrayList<ImageView> winDiscs) {
            if (BuildConfig.DEBUG) {
                Log.e(TAG, outcome.name());
            }
            if (outcome != BoardLogic.Outcome.NOTHING) {

                mWinnerView.setVisibility(VISIBLE);
                mPlayer1.turnIndicator.setVisibility(INVISIBLE);
                mPlayer2.turnIndicator.setVisibility(INVISIBLE);
                switch (outcome) {
                    case DRAW:
                        mWinnerView.setText(mContext.getString(R.string.draw)+" THIS ROUND! SCORE "+mPlayer1.score+" : "+mPlayer2.score);
                        break;
                    case PLAYER1_WINS:
                        mPlayer1.score++;
                        mWinnerView.setText(mContext.getString(R.string.you_win)+" THIS ROUND! SCORE "+mPlayer1.score+" : "+mPlayer2.score);
                        for (ImageView winDisc : winDiscs) {
                            winDisc.setImageResource(mGameRules.getRule(GameRules.DISC) == GameRules.Disc.RED ?
                                    R.drawable.win_red : R.drawable.win_yellow);
                        }
                        break;
                    case PLAYER2_WINS:
                        mPlayer2.score++;
                        mWinnerView.setText(mGameRules.getRule(GameRules.OPPONENT) == GameRules.Opponent.AI ?
                                mContext.getString(R.string.you_lose)+" THIS ROUND! SCORE "+mPlayer1.score+" : "+mPlayer2.score : mContext.getString(R.string.friend_win)+" "+mPlayer1.score+" : "+mPlayer2.score);
                        for (ImageView winDisc : winDiscs) {
                            winDisc.setImageResource(mGameRules.getRule(GameRules.DISC2) == GameRules.Disc.RED ?
                                    R.drawable.win_red : R.drawable.win_yellow);
                        }
                        break;
                    default:
                        break;
                }
            } else {
                mWinnerView.setVisibility(INVISIBLE);
            }
        }

}
