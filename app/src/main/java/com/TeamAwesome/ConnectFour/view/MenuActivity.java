package com.TeamAwesome.ConnectFour.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

import com.example.jorda.connect4.R;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        final Spinner Player1_color = (Spinner) findViewById(R.id.color_selector_player_1);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.player_colors, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        Player1_color.setAdapter(adapter);

        final Spinner Player2_color = (Spinner) findViewById(R.id.color_selector_player_2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.player_colors, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Player2_color.setAdapter(adapter2);


        final Spinner player1 = (Spinner) findViewById(R.id.type_selector_player_1);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.player_types, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        player1.setAdapter(adapter3);

        final Spinner player2 = (Spinner) findViewById(R.id.type_selector_player_2);
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this,
                R.array.player_types, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        player2.setAdapter(adapter4);

        final Spinner player1_difficulty = (Spinner) findViewById(R.id.difficulty_selector_player_1);
        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(this,
                R.array.player_difficulties, android.R.layout.simple_spinner_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        player1_difficulty.setAdapter(adapter5);

        final Spinner player2_difficulty = (Spinner) findViewById(R.id.difficulty_selector_player_2);
        ArrayAdapter<CharSequence> adapter6 = ArrayAdapter.createFromResource(this,
                R.array.player_difficulties, android.R.layout.simple_spinner_item);
        adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        player2_difficulty.setAdapter(adapter6);

        final Spinner Board_size = (Spinner) findViewById(R.id.board_size_selector);
        ArrayAdapter<CharSequence> adapter7 = ArrayAdapter.createFromResource(this,
                R.array.board_sizes, android.R.layout.simple_spinner_item);
        adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Board_size.setAdapter(adapter7);

        /*
        LinearLayout ll = (LinearLayout) findViewById(R.id.testid);
        TextView t = new TextView(this);
        t.setText("goodbye");
        t.setId(5);
        t.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        ll.addView(t);
        */

        final MenuController menuController = new MenuController();

        Player1_color.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                menuController.setPlayer1_color(Player1_color.getItemAtPosition(position).toString());
                //Toast.makeText(MenuActivity.this,"Test - Selected item : "+Player1_color.getItemAtPosition(position),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Player2_color.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                menuController.setPlayer2_color(Player2_color.getItemAtPosition(position).toString());
                //Toast.makeText(MenuActivity.this,"Test - Selected item : "+Player1_color.getItemAtPosition(position),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        player1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                menuController.setPlayer1(player1.getItemAtPosition(position).toString());
                //Toast.makeText(MenuActivity.this,"Test - Selected item : "+player1.getItemAtPosition(position),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        player2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                menuController.setPlayer2(player2.getItemAtPosition(position).toString());
                //Toast.makeText(MenuActivity.this,"Test - Selected item : "+player2.getItemAtPosition(position),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        player1_difficulty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                menuController.setPlayer1_difficulty(player1_difficulty.getItemAtPosition(position).toString());
                //Toast.makeText(MenuActivity.this,"Test - Selected item : "+player1_difficulty.getItemAtPosition(position),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        player2_difficulty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                menuController.setPlayer2_difficulty(player2_difficulty.getItemAtPosition(position).toString());
                //Toast.makeText(MenuActivity.this,"Test - Selected item : "+player1_difficulty.getItemAtPosition(position),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Board_size.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                menuController.setBoard_size(Board_size.getItemAtPosition(position).toString());
                //Toast.makeText(MenuActivity.this,"Test - Selected item : "+Board_size.getItemAtPosition(position),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    public void beginGame(View view)
    {
        Intent intent = new Intent(this, GamePlayActivity.class);
        startActivity(intent);
    }
}
