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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.example.harshil.tictactoe.R.drawable.red;
import static com.example.harshil.tictactoe.R.drawable.yellow;

public class device extends AppCompatActivity {

    LinearLayout tryAgain, startGame;
    ImageView winner;
    GridLayout imgGrid;
    TextView timesRed, timesYellow, drawn, totalGame;
    int value;
    boolean over = false;
    int timesRedwon = 0, timesYellowon = 0, timesDrawn = 0, total = 0;
    //0 for Red; 1 for yellow
    int turn, won = 2;
    //2 means un-played
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    //storing positions
    int[][] positions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    public void playGame(View view) {
        startGame = (LinearLayout) findViewById(R.id.startGame);
        startGame.setVisibility(View.GONE);
        while (!over && won == 2) {
            red();
            yellow();
        }

        if (over && won == 2) {
            timesDrawn += 1;
            total += 1;
            over = false;
            totalGame = (TextView) findViewById(R.id.textView3);
            totalGame.setText(String.valueOf(total));
            drawn = (TextView) findViewById(R.id.drawn);
            drawn.setText("DRAWN: " + timesDrawn);
            winner = (ImageView) findViewById(R.id.imageView21);
            winner.setImageResource(R.drawable.draw);
            tryAgain = (LinearLayout) findViewById(R.id.tryAgain);
            tryAgain.setVisibility(View.VISIBLE);
        }
    }

    public void red(){
        if (getRedPos(0) && won == 2 && turn == 0) {
            imgGrid = (GridLayout) findViewById(R.id.imgGrid);
            ((ImageView) imgGrid.getChildAt(value)).setImageResource(red);
            doAnimation();
            turn = 1;
            checkWon();
        }
    }

    public void yellow(){
        if (getYellowPos(1) && won == 2 && turn == 1) {
            imgGrid = (GridLayout) findViewById(R.id.imgGrid);
            ((ImageView) imgGrid.getChildAt(value)).setImageResource(yellow);
            doAnimation();
            turn = 0;
            checkWon();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Device");
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_device, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_home) {
            Intent home = new Intent(getApplicationContext(), home.class);
            startActivity(home);
        }

        return super.onOptionsItemSelected(item);
    }

    public void doAnimation() {
        imgGrid.getChildAt(value).animate().alpha(1f).rotation(360f).setDuration(500);
        if (imgGrid.getChildAt(value).getRotation() == 360f) {
            imgGrid.getChildAt(value).setRotation(0f);
            (imgGrid.getChildAt(value)).animate().alpha(1f).rotation(360f).setDuration(500);
        }
    }

    public void tryAgain(View view) {
        reset();
        startGame.performClick();
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

    public boolean getYellowPos(int turn) {
        List dev = new ArrayList();
        for (int i = 0; i < gameState.length; i++) {
            if (gameState[i] == 2) {
                dev.add(i);
            }
        }
        if (dev.size() != 0) {
            int index = new Random().nextInt(dev.size());
            for (int[] position : positions) {
                List redDev = new ArrayList();
                List yellowDev = new ArrayList();
                for (int i = 0; i < position.length; i++) {
                    for (int[] yellowPos : positions) {
                        yellowDev = new ArrayList();
                        for (int j = 0; j < yellowPos.length; j++) {
                            if (gameState[yellowPos[j]] == 1) {
                                yellowDev.add(yellowPos[j]);
                            }
                            if (yellowDev.size() == 2) {
                                for (int y = 0; y < yellowPos.length; y++) {
                                    if (gameState[yellowPos[y]] == 2) {
                                        value = yellowPos[y];
                                        gameState[value] = turn;
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                    if (gameState[position[i]] == 0) {
                        redDev.add(position[i]);
                    }
                }
                if (redDev.size() == 2) {
                    for (int i = 0; i < position.length; i++) {
                        if (gameState[position[i]] == 2) {
                            value = position[i];
                            gameState[value] = turn;
                            return true;
                        }
                    }
                }
            }
            int corner = 0;
            for (int i = 0; i < 4; i++) {
                if (gameState[corner] == 0 && gameState[4] == 2) {
                    value = 4;
                    gameState[value] = turn;
                    return true;
                }
                if (corner == 2) {
                    corner += 4;
                } else {
                    corner += 2;
                }
            }
            value = (Integer) dev.get(index);
            gameState[value] = turn;
            return true;
        } else {
            return false;
        }
    }

    public void checkWon() {
        for (int[] position : positions) {
            if (gameState[position[0]] == gameState[position[1]] && gameState[position[1]] == gameState[position[2]] && gameState[position[0]] != 2) {
                won = gameState[position[0]];
                winner = (ImageView) findViewById(R.id.imageView21);
                totalGame = (TextView) findViewById(R.id.textView3);
                if (won == 0) {
                    winner.setImageResource(R.drawable.redwon);
                    timesRedwon += 1;
                    total += 1;
                    totalGame.setText(String.valueOf(total));
                } else if (won == 1) {
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
    }

    public boolean getRedPos(int turn) {
        List dev = new ArrayList();
        for (int i = 0; i < gameState.length; i++) {
            if (gameState[i] == 2) {
                dev.add(i);
            }
        }
        if (dev.size() != 0) {
            int index = new Random().nextInt(dev.size());
            for (int[] position : positions) {
                List yellowDev = new ArrayList();
                List redDev = new ArrayList();
                for (int i = 0; i < position.length; i++) {
                    for (int[] redPos : positions) {
                        redDev = new ArrayList();
                        for (int j = 0; j < redPos.length; j++) {
                            if (gameState[redPos[j]] == 0) {
                                redDev.add(redPos[j]);
                            }
                            if (redDev.size() == 2) {
                                for (int y = 0; y < redPos.length; y++) {
                                    if (gameState[redPos[y]] == 2) {
                                        value = redPos[y];
                                        gameState[value] = turn;
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                    if (gameState[position[i]] == 1) {
                        yellowDev.add(position[i]);
                    }
                }
                if (yellowDev.size() == 2) {
                    for (int i = 0; i < position.length; i++) {
                        if (gameState[position[i]] == 2) {
                            value = position[i];
                            gameState[value] = turn;
                            return true;
                        }
                    }
                }
            }
            int corner = 0;
            for (int i = 0; i < 4; i++) {
                if (gameState[corner] == 1 && gameState[4] == 2) {
                    value = 4;
                    gameState[value] = turn;
                    return true;
                }
                if (corner == 2) {
                    corner += 4;
                } else {
                    corner += 2;
                }
            }
            value = (Integer) dev.get(index);
            gameState[value] = turn;
            return true;
        } else {
            return false;
        }
    }
}
