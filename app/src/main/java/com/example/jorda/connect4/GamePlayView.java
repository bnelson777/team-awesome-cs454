package com.example.jorda.connect4;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by school on 2/28/2018.
 */

public class GamePlayView extends RelativeLayout {
    protected Context mContext;
    protected View mBoardView;

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
        inflate(context, R.layout.game_board1, this);

        mBoardView = findViewById(R.id.game_board1 );
    }
}
