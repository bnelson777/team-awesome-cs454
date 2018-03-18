package com.example.jorda.connect4;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Created by school on 2/28/2018.
 */

public class GamePlayViewSmall extends GamePlayView {
    public GamePlayViewSmall(Context context) {
        super(context);
        init(context);
    }

    public GamePlayViewSmall(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public GamePlayViewSmall(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.gameplay_small, this);
        mBoardView = findViewById(R.id.gameplaySmall);
    }
}
