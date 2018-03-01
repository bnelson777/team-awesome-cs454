package com.example.jorda.connect4;

import android.content.Context;
import android.util.AttributeSet;
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
    private View mBoardView;

    public GamePlayView(Context context)
    {
        super(context);
        inflate(context, R.layout.activity_gameplay, this);
        Log.wtf("GamePlayView", "constructor");
        //mBoardView = findViewById(R.id.game_board);
    }

    public GamePlayView(Context context, AttributeSet attrs)
    {
        super(context);
    }

    public GamePlayView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs);
    }

    /*
     * Create new board of given size,
     * initializing all cells.
     * Set the cells' image to blank,
     * set it's listener to the GamePlayController
     */
    public void adjustSize(int width, int height, GamePlayController listener) {
/*
        for (int x = 0; x < width; x++) {
            ViewGroup currentRow = (ViewGroup) ((ViewGroup) mBoardView).getChildAt(x);
            for (int y = 0; y < height; y++) {
                // Do we need to setImageResource, or can we skip this?
                ((ImageView) currentRow.getChildAt(y)).setImageResource(android.R.color.transparent);
                (currentRow.getChildAt(y)).setOnClickListener(listener);
            }
        }
        */
    }

    public void animateDiskPlacement(int row, int column, int image_id)
    {
        //ImageView cell = mCells[row][column];
        ViewGroup rowgroup = (ViewGroup) ((ViewGroup) mBoardView).getChildAt(row);
        ImageView cell = (ImageView) rowgroup.getChildAt(column);

        cell.setY( -cell.getHeight()*(row+1) );
        cell.setImageResource(image_id);
        cell.animate().translationY(0).setInterpolator(new BounceInterpolator()).start();
    }

    public float getCellWidth()
    {
        // Get 1st cell in 1st row of board, and get that cell's width
        return ((ImageView) ((ViewGroup) ((ViewGroup) mBoardView).getChildAt(0)).getChildAt(0)).getWidth();
    }
}
