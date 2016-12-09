package com.example.harshil.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;

public class home extends AppCompatActivity {

    RelativeLayout relativeLayout;

    public void color(View view) {
        Intent activitymain = new Intent(getApplicationContext(), device.class);
        startActivity(activitymain);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void singlePlayer(View view) {
        Intent activitymain = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(activitymain);
    }

    public void doublePlayer(View view) {
        Intent activitymain = new Intent(getApplicationContext(), double_player.class);
        startActivity(activitymain);
    }
}
