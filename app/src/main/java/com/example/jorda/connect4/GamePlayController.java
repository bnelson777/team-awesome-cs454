package com.example.jorda.connect4;

import android.app.AlertDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

/**
 * Created by school on 2/28/2018.
 */

public class GamePlayController implements View.OnClickListener {
    private Context mContext;

    private GamePlayActivity mGamePlay;
    private GamePlayView mPlayView;

    public GamePlayController(Context context, GamePlayActivity GamePlay_in, GamePlayView PlayView_in)
    {
        mContext = context;

        mGamePlay = GamePlay_in;
        mPlayView = PlayView_in;
    }

    @Override
    public void onClick(@NonNull View v)
    {
        // Give player-selected column to GamePlayActivity, to
        // actually do the move

        Log.wtf( "","Controller click, passing view");

        mGamePlay.doMove( (int)v.getX() / (int) mPlayView.getCellWidth() );
    }
}
