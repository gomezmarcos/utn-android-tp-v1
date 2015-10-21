package com.gomezmarcos.juegodelamemoria;

import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Space;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class GameActivity extends ActionBarActivity {
    List<Button> elements = new ArrayList<>();
    Button firstGuess = null;
    Button secondGuess = null;
    int guessed=0;
    int lifes=3;
    String timeSpent="00:00";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        GridLayout board = (GridLayout) findViewById(R.id.gridTable);
        board.removeAllViews();

        int columns = 4;
        int rows = 3;
        board.setColumnCount(columns);
        board.setRowCount(rows);

        addButtonToBoard(R.drawable.img_1);
        addButtonToBoard(R.drawable.img_1);
        addButtonToBoard(R.drawable.img_2);
        addButtonToBoard(R.drawable.img_2);
        addButtonToBoard(R.drawable.img_3);
        addButtonToBoard(R.drawable.img_3);
        addButtonToBoard(R.drawable.img_4);
        addButtonToBoard(R.drawable.img_4);
        addButtonToBoard(R.drawable.img_5);
        addButtonToBoard(R.drawable.img_5);
        addButtonToBoard(R.drawable.img_6);
        addButtonToBoard(R.drawable.img_6);

        Collections.shuffle(elements);

        for(Button b: elements){
            board.addView(b);
        }

    }

    private void addButtonToBoard(int drawable) {
        Button btn = new Button(this);
        btn.setTag(drawable);
        btn.setBackgroundResource(R.drawable.question_icon);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = elements.indexOf((Button) view);
                if (firstGuess == null) {
                    firstGuess = (Button) view;
                    firstGuess.setBackgroundResource((Integer) firstGuess.getTag());
                } else if (secondGuess == null) {
                    if (firstGuess == (Button) view) {
                        return;
                    }
                    secondGuess = (Button) view;
                    secondGuess.setBackgroundResource((Integer) secondGuess.getTag());
                    if (firstGuess.getTag().toString().equals(secondGuess.getTag().toString())) {
                        Toast.makeText(GameActivity.this, "Son iguales", Toast.LENGTH_SHORT).show();
                        guessed++;
                        if (guessed == 6)
                            Toast.makeText(GameActivity.this,"GANASTE EL JUEGO",Toast.LENGTH_LONG).show();
                    } else {
                        if(--lifes == 0)
                            Toast.makeText(GameActivity.this,"PERDISTE EL JUEGO",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(GameActivity.this, "Son distintos burrito!", Toast.LENGTH_SHORT).show();

                        firstGuess.setBackgroundResource(R.drawable.question_icon);
                        secondGuess.setBackgroundResource(R.drawable.question_icon);
                    }
                    firstGuess = null;
                    secondGuess = null;
                }
            }
        });
        elements.add(btn);
    }

}
