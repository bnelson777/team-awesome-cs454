package com.TeamAwesome.ConnectFour.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.RadioButton;

import com.TeamAwesome.ConnectFour.R;
import com.TeamAwesome.ConnectFour.controller.GameMenuController;
import com.TeamAwesome.ConnectFour.rules.GameRules;
import com.TeamAwesome.ConnectFour.view.MenuView;

public class GameMenuActivity extends AppCompatActivity implements GameMenuController.MenuControllerListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_menu);
        MenuView menuView = (MenuView) findViewById(R.id.menuView);
        GameMenuController gameMenuController =new GameMenuController(this, menuView);
        menuView.setListeners(gameMenuController);

    }

    @Override
    public void onPlay(@NonNull GameRules gameRules) {
        final RadioButton b1 = (RadioButton) findViewById(R.id.board_one);
        final RadioButton b2 = (RadioButton) findViewById(R.id.board_two);
        final RadioButton b3 = (RadioButton) findViewById(R.id.board_three);
        if (b1.isChecked()) {
            Intent gamePlayIntent1 = new Intent( GameMenuActivity.this, GamePlayActivity1.class);
            gamePlayIntent1.putExtras(gameRules.exportTo(new Bundle()));
            startActivity(gamePlayIntent1);
        }

        else if (b2.isChecked()) {
            Intent gamePlayIntent2 = new Intent(GameMenuActivity.this, GamePlayActivity2.class);
            gamePlayIntent2.putExtras(gameRules.exportTo(new Bundle()));
            startActivity(gamePlayIntent2);
        }

        else if (b3.isChecked()) {
            Intent gamePlayIntent3 = new Intent(GameMenuActivity.this, GamePlayActivity3.class);
            gamePlayIntent3.putExtras(gameRules.exportTo(new Bundle()));
            startActivity(gamePlayIntent3);
        }
    }
}
