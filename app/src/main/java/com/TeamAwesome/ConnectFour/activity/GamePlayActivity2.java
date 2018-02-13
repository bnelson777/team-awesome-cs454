package com.TeamAwesome.ConnectFour.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.text.InputType;// added

import com.TeamAwesome.ConnectFour.R;
import com.TeamAwesome.ConnectFour.controller.GamePlayController2;
import com.TeamAwesome.ConnectFour.rules.GameRules;
import com.TeamAwesome.ConnectFour.view.BoardView2;

public class GamePlayActivity2 extends AppCompatActivity {

    private GamePlayController2 mGameController;
    private final GameRules mGameRules = new GameRules();


    private int NumberOfRounds=0; //Added by Soyoung

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2);

        BoardView2 boardView2 = (BoardView2) findViewById(R.id.gameView2);
        mGameRules.importFrom(getIntent().getExtras());
        mGameController = new GamePlayController2(this, boardView2, mGameRules);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_close);

        if(mGameController.getNumberOfRounds()==0) { //Added by Soyoung

            AlertDialog.Builder alert = new AlertDialog.Builder(this);

            alert.setTitle("The number of rounds");
            alert.setMessage("How many rounds would you like to play?");

            final EditText rounds = new EditText(this);
            rounds.setInputType(InputType.TYPE_CLASS_NUMBER);

            alert.setView(rounds);
            alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    String temp = rounds.getText().toString();
                    NumberOfRounds = Integer.parseInt(temp);

                    Log.d("CREATION", "soyoung test1 : " + NumberOfRounds);
                    mGameController.setNumberOfRounds(NumberOfRounds);
                }
            });

            alert.show();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                showAlert(R.string.back);
                break;
            case R.id.restart:
                showAlert(R.string.reset_game);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showAlert(final int msgId) {
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.app_name))
                .setMessage(msgId)
                .setCancelable(false)
                .setNegativeButton(R.string.no, null)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (msgId == R.string.back) {
                            mGameController.exitGame();
                        } else {
                            mGameController.restartGame();
                        }
                    }
                }).show();
    }

    @Override
    public void onBackPressed() {
        showAlert(R.string.back);
    }
}
