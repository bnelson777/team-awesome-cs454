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
import java.util.ArrayList;
import com.TeamAwesome.ConnectFour.utils.BoardType;

    public class BoardView2 extends BoardView {

        public BoardView2(Context context) {
            super(context);
            init(context);
        }

        public BoardView2(Context context, AttributeSet attrs) {
            super(context, attrs);
            init(context);
        }

        public BoardView2(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            init(context);
        }

    private void init(Context context) {
        this.mContext = context;
        inflate(context, R.layout.game_board2, this);

        mPlayer1 = new PlayerInformation(R.id.player1_name, R.id.player1_disc, R.id.player1_indicator);
        mPlayer2 = new PlayerInformation(R.id.player2_name, R.id.player2_disc, R.id.player2_indicator);

        mBoardView = findViewById(R.id.game_board2 );
        mWinnerView = (TextView) findViewById(R.id.winner_text);
    }
}
