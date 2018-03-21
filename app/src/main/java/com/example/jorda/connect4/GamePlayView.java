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
        public final int piece;
        public final int winPiece;

        public PlayerInformation(int player_name_id, int player_disc_id, int player_piece, int player_win_piece) {
            name = findViewById(player_name_id);
            disc = findViewById(player_disc_id);
            piece = player_piece;
            winPiece = player_win_piece;
        }
    }

    private GamePlayController mListener;
    public PlayerInformation mPlayer1; //changed it to be public
    public PlayerInformation mPlayer2;
    protected ImageView[][] mCells;
    protected View mBoardView;
    protected TextView mWinnerView;
    protected TextView mRoundView;
    protected Context mContext;

    public GamePlayView(Context context)
    {
        super(context);
    }

    public GamePlayView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public GamePlayView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    public void initialize(GamePlayController gamePlayController,  int cols_in, int rows_in, int p1piece, int p1winPiece, int p2piece, int p2winPiece) {
        mWinnerView = findViewById(R.id.winner_text);
        mRoundView = findViewById(R.id.current_round_text);
        mPlayer1 = new PlayerInformation(R.id.player1_name, R.id.player1_disc, p1piece, p1winPiece);
        mPlayer2 = new PlayerInformation(R.id.player2_name, R.id.player2_disc, p2piece, p2winPiece);
        mListener = gamePlayController;

        highlightPlayer(1);
        unhighlightPlayer(2);

        // Get a grid of image views for all the board cells
        mCells = new ImageView[rows_in][cols_in];
        for (int r = 0; r < rows_in; r++) {
            ViewGroup row = (ViewGroup) ((ViewGroup) mBoardView).getChildAt(r);
            row.setClipChildren(false);
            for (int c = 0; c < cols_in; c++) {
                ImageView imageView = (ImageView) row.getChildAt(c);
                imageView.setImageResource(android.R.color.transparent);
                imageView.setOnClickListener(mListener);
                mCells[r][c] = imageView;
            }
        }
    }

    public void dropDisc(int row, int col, int resource) {
        Log.wtf("dropDisc",row+" : "+col);
        int row1 = row-1;
        int col2 = col;
        Log.wtf("dropDisc 2 ",row1+" : "+col2);
        final ImageView cell = mCells[row1][col2];
        float move = -(cell.getHeight() * row1 + cell.getHeight() + 15);
        cell.setY(move);
        cell.setImageResource(resource);
        cell.animate().translationY(0).setInterpolator(new BounceInterpolator()).start();
    }

    public void resetDiscs(int row, int col) {
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                final ImageView cell = mCells[i][j];
                float move = -(cell.getHeight() * row + cell.getHeight() + 15);
                cell.setY(move);
                //cell.setImageResource(android.R.color.transparent);
            }
        }
        //cell.animate().translationY(0).setInterpolator(new BounceInterpolator()).start();
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
