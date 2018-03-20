package com.example.jorda.connect4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;


public class RulesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);
        TextView tv = findViewById(R.id.gameRules);
        Log.wtf("Rules","initialized");
        tv.setText(Html.fromHtml(getString(R.string.rules_text)));
    }
}
