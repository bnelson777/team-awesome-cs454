package com.example.jorda.connect4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeScreenActivity extends AppCompatActivity {

    private Button localGame;
    private Button onlineGame;
    private Button highScores;
    private TextView welcomeText;
    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    public Player player1 = new Player(2);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        localGame = findViewById(R.id.btnLocalGame);
        onlineGame = findViewById(R.id.btnOnlineGame);
        highScores = findViewById(R.id.btnHighScores);
        welcomeText = findViewById(R.id.tvWelcome);

        String uid = firebaseAuth.getCurrentUser().getUid();
        DatabaseReference reference = mDatabase.getReference().child("users/" + uid + "/name");

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String name = dataSnapshot.getValue().toString();
                player1.setName(name);
                welcomeText.setText("Welcome " + player1.getName() + "!");

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        localGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeScreenActivity.this, MenuActivity.class));
            }
        });
        onlineGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeScreenActivity.this, OnlineActivity.class));
            }
        });
        highScores.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeScreenActivity.this, DemoGameActivity.class);
                i.putExtra("player1", player1.getName());
                startActivity(i);
                finish();
            }
        });
    }
}
