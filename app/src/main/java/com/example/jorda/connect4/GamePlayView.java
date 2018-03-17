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

public class GamePlayView extends RelativeLayout {
    protected class PlayerInformation {
        @NonNull
        public final TextView name;
        @NonNull
        public final ImageView disc;
        public final View turnIndicator;
        public final int piece;
        public final int winPiece;

        public PlayerInformation(int player_name_id, int player_disc_id, int player_piece, int player_win_piece) {
            name = findViewById(player_name_id);
            disc = findViewById(player_disc_id);
            piece = player_piece;
            winPiece = player_win_piece;
            turnIndicator = null;
        }
    }

    private int COLS;
    private int ROWS;
    private GamePlayController mListener;
    public PlayerInformation mPlayer1; //changed it to be public
    public PlayerInformation mPlayer2;
    protected ImageView[][] mCells;
    protected View mBoardView;
    protected TextView mWinnerView;
    protected TextView mRoundView;
    protected Context mContext;

    public GamePlayView(Context context) {
        super(context);
    }

    public GamePlayView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GamePlayView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void initialize(GamePlayController gamePlayController,  int cols_in, int rows_in, int p1piece, int p1winPiece, int p2piece, int p2winPiece) {
        mBoardView = findViewById(R.id.gameplaySmall);
        mWinnerView = findViewById(R.id.winner_text);
        mRoundView = findViewById(R.id.current_round_text);
        mPlayer1 = new PlayerInformation(R.id.player1_name, R.id.player1_disc, p1piece, p1winPiece);
        mPlayer2 = new PlayerInformation(R.id.player2_name, R.id.player2_disc, p2piece, p2winPiece);

        this.COLS = cols_in;
        this.ROWS = rows_in;
        this.mListener = gamePlayController;
        highlightPlayer(1);
        unhighlightPlayer(2);
        buildCells();
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

    public void highlightPlayer(int player)
    {
        if (player == 1) {
            //Log.wtf("View","highlighting player 1");
            mPlayer1.disc.setImageResource(mPlayer1.winPiece);
        }
            else
            mPlayer2.disc.setImageResource(mPlayer2.winPiece);
    }

    public void unhighlightPlayer(int player)
    {
        if (player == 1)
            mPlayer1.disc.setImageResource(mPlayer1.piece);
        else
            mPlayer2.disc.setImageResource(mPlayer2.piece);
    }

    // Highlight a square, to indicate a winning piece
    public void highlight(int x, int y, int imageresource_id)
    {
        //mCells[ROWS - 1 - y][x].setImageResource(imageresource_id);
        mCells[mCells.length - 1 - y][x].setImageResource(imageresource_id);
    }

    public void showRounds(int current, int total)
    {
        mRoundView.setText(""+current+"/"+total);
    }

    public void winMessage(String message)
    {
        mWinnerView.setText(message);
    }

}
