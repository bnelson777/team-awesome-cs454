package com.example.jorda.connect4;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by school on 2/28/2018.
 */

public class GamePlayView extends RelativeLayout {
    private int COLS;
    private int ROWS;
    private GamePlayController mListener;

    /**
     * view holder for player information
     */
    protected class PlayerInformation {
        @NonNull
        public final TextView name;
        @NonNull
        public final ImageView disc;
        public final View turnIndicator;

        public PlayerInformation(int player_name_id, int player_disc_id, int player_indicator_id) {
            name = (TextView) findViewById(player_name_id);
            disc = (ImageView) findViewById(player_disc_id);
            turnIndicator = findViewById(player_indicator_id);
        }
    }

    public PlayerInformation mPlayer1; //changed it to be public
    public PlayerInformation mPlayer2;

    private ImageView[][] mCells;
    protected View mBoardView;
    protected TextView mWinnerView;
    protected Context mContext;

    public GamePlayView(Context context) {
        super(context);
        init(context);
    }

    public GamePlayView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public GamePlayView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        if (false) {

            inflate(context, R.layout.game_board1, this);
            mBoardView = findViewById(R.id.game_board1);
        } else {
            inflate(context, R.layout.gameplay, this);
            mBoardView = findViewById(R.id.gameplay );
        }

        mWinnerView = (TextView) findViewById(R.id.winner_text);

        mPlayer1 = new PlayerInformation(R.id.player1_name, R.id.player1_disc, R.id.player1_indicator);
        mPlayer2 = new PlayerInformation(R.id.player2_name, R.id.player2_disc, R.id.player2_indicator);

    }

    public void initialize(GamePlayController gamePlayController,  int cols_in, int rows_in) {
        this.COLS = cols_in;
        this.ROWS = rows_in;
        this.mListener = gamePlayController;
        setPlayer1("Henry");
        setPlayer2();
        togglePlayer(1);
        buildCells();
    }

    /**
     * initialize mPlayer1 information with Gameules
     */
    private void setPlayer1(String player1) {
        mPlayer1.disc.setImageResource(R.drawable.player_piece_1);
        mPlayer1.name.setText(player1);
    }

    /**
     * initialize mPlayer2 information with Gameules
     */
    private void setPlayer2() {
        mPlayer2.disc.setImageResource(R.drawable.player_piece_2);
        mPlayer2.name.setText(R.string.default_player_2);
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
     * Drop a disc of the current player at available row of selected column
     *
     * @param col column to drop disc
     * @param row row of the column
     */
    public void dropDisc(int row, int col, final int playerTurn) {
        final ImageView cell = mCells[row][col];
        float move = -(cell.getHeight() * row + cell.getHeight() + 15);
        cell.setY(move);
        cell.setImageResource(playerTurn == 1 ?
                R.drawable.player_piece_1 : R.drawable.player_piece_2);
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
        mPlayer1.turnIndicator.setVisibility(playerTurn == 1 ? VISIBLE : INVISIBLE);
        mPlayer2.turnIndicator.setVisibility(playerTurn == 2 ? VISIBLE : INVISIBLE);
    }
}
