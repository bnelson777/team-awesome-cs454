package com.TeamAwesome.ConnectFour.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.text.InputType;
import android.util.Log;
import android.widget.EditText;
import android.widget.RadioButton;

import com.TeamAwesome.ConnectFour.R;
import com.TeamAwesome.ConnectFour.controller.GameMenuController;
import com.TeamAwesome.ConnectFour.rules.GameRules;
import com.TeamAwesome.ConnectFour.view.MenuView;
import com.TeamAwesome.ConnectFour.utils.BoardType;

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
        Intent intent = getIntent();
        String p1name = intent.getStringExtra("p1Name");
        /*
         * Warning! kludgey hard-coded values ahead!
         */
        if (b1.isChecked()) {
            Intent gamePlayIntent1 = new Intent( GameMenuActivity.this, GamePlayActivity.class);
            gamePlayIntent1.putExtras(gameRules.exportTo(new Bundle()));
            gamePlayIntent1.putExtra("boardType", BoardType.BoardTypeOne);
            gamePlayIntent1.putExtra("boardWidth", 7);
            gamePlayIntent1.putExtra("boardHeight", 6);
            gamePlayIntent1.putExtra("p1name", p1name);
            startActivity(gamePlayIntent1);
        }

        else if (b2.isChecked()) {
            Intent gamePlayIntent2 = new Intent(GameMenuActivity.this, GamePlayActivity.class);
            gamePlayIntent2.putExtras(gameRules.exportTo(new Bundle()));
            gamePlayIntent2.putExtra("boardType", BoardType.BoardTypeTwo);
            gamePlayIntent2.putExtra("boardWidth", 8);
            gamePlayIntent2.putExtra("boardHeight", 7);
            gamePlayIntent2.putExtra("p1name", p1name);
            startActivity(gamePlayIntent2);
        }

        else if (b3.isChecked()) {
            Intent gamePlayIntent3 = new Intent(GameMenuActivity.this, GamePlayActivity.class);
            gamePlayIntent3.putExtras(gameRules.exportTo(new Bundle()));
            gamePlayIntent3.putExtra("boardType", BoardType.BoardTypeThree);
            gamePlayIntent3.putExtra("boardWidth",10);
            gamePlayIntent3.putExtra("boardHeight",8);
            gamePlayIntent3.putExtra("p1name", p1name);
            startActivity(gamePlayIntent3);
        }
    }
}
