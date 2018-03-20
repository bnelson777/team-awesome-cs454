package com.example.jorda.connect4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class CreditsActivity extends AppCompatActivity {

    private Button localGame;
    private Button onlineGame;
    private Button highScores;
    private TextView welcomeText;
    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    public Player player1 = new Player(1);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);
        TextView tv = findViewById(R.id.gameCredits);
        tv.setText(Html.fromHtml(getString(R.string.credits_text)));
        tv.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
