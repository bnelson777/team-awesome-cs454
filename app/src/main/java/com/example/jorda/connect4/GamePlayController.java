package com.example.jorda.connect4;

import android.support.annotation.NonNull;
import android.view.View;

/**
 * Created by school on 2/28/2018.
 */

public class GamePlayController {
    private GamePlayActivity mGamePlay;
    private GamePlayView mPlayView;
    private float cellWidth;

    @Override
    public void onClick(@NonNull View v)
    {
        mGamePlay.doMove( (int)v.getX() / (int) cellWidth);
    }
}
