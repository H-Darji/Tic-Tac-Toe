package com.example.harshil.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class double_player extends AppCompatActivity {

    LinearLayout tryAgain;
    ImageView winner;
    GridLayout imgGrid;
    TextView timesRed, timesYellow, drawn, totalGame;
    boolean over;
    int timesRedwon = 0, timesYellowon = 0, timesDrawn = 0, total = 0;

    //0 for Red; 1 for yellow
    int turn = 0, won = 2;
    //2 means un-played
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    //storing positions
    int[][] positions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    public void fadeIn(View view) {
        ImageView counter = (ImageView) view;
        winner = (ImageView) findViewById(R.id.imageView21);
        int tapped = Integer.parseInt(counter.getTag().toString());

        if (gameState[tapped] == 2) {
            counter.setAlpha(0f);
            gameState[tapped] = turn;
            if (turn == 0) {
                counter.setImageResource(R.drawable.red);
                turn = 1;
            } else {
                counter.setImageResource(R.drawable.yellow);
                turn = 0;
            }
            counter.animate().alpha(1f).rotation(360f).setDuration(500);
            if (counter.getRotation() == 360f) {
                counter.setRotation(0f);
                counter.animate().alpha(1f).rotation(360f).setDuration(500);
            }
        }

        for (int[] position : positions) {
            if (gameState[position[0]] == gameState[position[1]] && gameState[position[1]] == gameState[position[2]] && gameState[position[0]] != 2) {
                won = gameState[position[0]];
                totalGame = (TextView) findViewById(R.id.textView3);
                if (won == 0) {
                    winner.setImageResource(R.drawable.redwon);
                    timesRedwon += 1;
                    total += 1;
                    totalGame.setText(String.valueOf(total));
                } else {
                    winner.setImageResource(R.drawable.yellowwon);
                    timesYellowon += 1;
                    total += 1;
                    totalGame.setText(String.valueOf(total));
                }
                timesRed = (TextView) findViewById(R.id.timesRed);
                timesYellow = (TextView) findViewById(R.id.timesYellow);
                timesRed.setText(String.valueOf(timesRedwon));
                timesYellow.setText(String.valueOf(timesYellowon));
                tryAgain = (LinearLayout) findViewById(R.id.tryAgain);
                tryAgain.setVisibility(View.VISIBLE);
            } else {
                over = true;
                for (int current : gameState) {
                    if (current == 2) {
                        over = false;
                        break;
                    }
                }
            }
        }
        if (over && won == 2) {
            timesDrawn += 1;
            total += 1;
            totalGame = (TextView) findViewById(R.id.textView3);
            totalGame.setText(String.valueOf(total));
            drawn = (TextView) findViewById(R.id.drawn);
            drawn.setText("DRAWN: " + timesDrawn);
            winner.setImageResource(R.drawable.draw);
            tryAgain = (LinearLayout) findViewById(R.id.tryAgain);
            tryAgain.setVisibility(View.VISIBLE);
        }
    }

    public void tryAgain(View view) {
        reset();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_double_player);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Double Player");
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_double, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_reset) {
            timesRedwon = 0;
            timesYellowon = 0;
            timesDrawn = 0;
            total = 0;
            totalGame = (TextView) findViewById(R.id.textView3);
            totalGame.setText(String.valueOf(total));
            drawn = (TextView) findViewById(R.id.drawn);
            drawn.setText("DRAWN: " + timesDrawn);
            timesRed = (TextView) findViewById(R.id.timesRed);
            timesYellow = (TextView) findViewById(R.id.timesYellow);
            timesRed.setText(String.valueOf(timesRedwon));
            timesYellow.setText(String.valueOf(timesYellowon));
            reset();
        } else if (id == R.id.action_home) {
            Intent home = new Intent(getApplicationContext(), home.class);
            startActivity(home);
        }

        return super.onOptionsItemSelected(item);
    }

    public void reset() {
        turn = 0;
        won = 2;
        tryAgain = (LinearLayout) findViewById(R.id.tryAgain);
        tryAgain.setVisibility(View.GONE);
        winner.setImageResource(0);
        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }
        imgGrid = (GridLayout) findViewById(R.id.imgGrid);
        for (int i = 0; i < imgGrid.getChildCount(); i++) {
            ((ImageView) imgGrid.getChildAt(i)).setImageResource(0);
        }
    }

}
