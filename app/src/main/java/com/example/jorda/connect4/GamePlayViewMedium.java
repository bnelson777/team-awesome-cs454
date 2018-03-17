package com.example.jorda.connect4;

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

/**
 * Created by school on 2/28/2018.
 */

public class GamePlayViewMedium extends GamePlayView {
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

        public PlayerInformation(int player_name_id, int player_disc_id) {
            name = (TextView) findViewById(player_name_id);
            disc = (ImageView) findViewById(player_disc_id);
            turnIndicator = null;
        }
    }

    public PlayerInformation mPlayer1; //changed it to be public
    public PlayerInformation mPlayer2;

    private ImageView[][] mCells;
    protected View mBoardView;
    protected TextView mWinnerView;
    protected Context mContext;

    public GamePlayViewMedium(Context context) {
        super(context);
        init(context);
    }

    public GamePlayViewMedium(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public GamePlayViewMedium(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;

            inflate(context, R.layout.gameplay_medium, this);
            mBoardView = findViewById(R.id.gameplayMedium);

        mWinnerView = (TextView) findViewById(R.id.winner_text);

        mPlayer1 = new PlayerInformation(R.id.player1_name, R.id.player1_disc);
        mPlayer2 = new PlayerInformation(R.id.player2_name, R.id.player2_disc);

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

    private void setPlayer1(String player1) {
        mPlayer1.disc.setImageResource(R.drawable.player_piece_white);
        mPlayer1.name.setText(player1);
    }

    private void setPlayer2() {
        mPlayer2.disc.setImageResource(R.drawable.player_piece_black);
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

    public void dropDisc(int row, int col, int resource) {
        final ImageView cell = mCells[row][col];
        float move = -(cell.getHeight() * row + cell.getHeight() + 15);
        cell.setY(move);
        cell.setImageResource(resource);
        cell.animate().translationY(0).setInterpolator(new BounceInterpolator()).start();
    }

    public float getCellWidth() {
        return mCells[0][0].getWidth();
    }

    public void togglePlayer(int playerTurn) {
        mPlayer1.turnIndicator.setVisibility(playerTurn == 1 ? VISIBLE : INVISIBLE);
        mPlayer2.turnIndicator.setVisibility(playerTurn == 2 ? VISIBLE : INVISIBLE);
    }

    // Highlight a square, to indicate a winning piece
    public void highlight(int x, int y, int imageresource_id)
    {
        Log.wtf("view", "highlighting "+x+" "+y);
        mCells[ROWS - 1 - y][x].setImageResource(imageresource_id);
    }
}
