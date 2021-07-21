package com.company.numberguessinggame;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class GamePlay extends AppCompatActivity {
    TextView previousGuess;
    TextView livesNbr;
    TextView action;
    EditText guessANbr;
    Button confirm;
    String comparison = "";
    int rightNbr;
    int lives = 10;
    String guess;
    ArrayList<String> guesses = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_play);

        previousGuess = findViewById(R.id.previousGuess);
        livesNbr = findViewById(R.id.livesNbr);
        action = findViewById(R.id.actionText);
        guessANbr = findViewById(R.id.guessEdit);
        confirm = findViewById(R.id.confirmBtn);

        Intent intent = getIntent();
        rightNbr = intent.getIntExtra("rightNbr", 0);

        confirm.setOnClickListener(v -> {
            guess = guessANbr.getText().toString();
            if (guess.equals("")){
                guessANbr.setText("");
            }
            else if (!guess.equals(Integer.toString(rightNbr)) && lives > 1) {
                previousGuess.setText(String.format("Your last guess: %s", guess));
                guesses.add(guess);
                lives--;
                livesNbr.setText(String.format("Your lives: %s", lives));
                comparison = Integer.parseInt(guess) < rightNbr? "Increase" : "Decrease";
                action.setText(String.format("%s your guess", comparison));
                guessANbr.setText("");
            }
            else if (!guess.equals(Integer.toString(rightNbr)) && lives == 1) {
                lives--;
                livesNbr.setText(String.format("Your lives: %s", lives));
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                alertDialog
                        .setTitle("Game Over")
                        .setMessage(String.format("My guess was %s.\nYour guesses: %s\nWould you like to play again?", guess, Arrays.toString(guesses.toArray())))
                        .setCancelable(false)
                        .setNegativeButton("No", (dialog, which) -> dialog.cancel())
                        .setPositiveButton("Yes", (dialog, which) -> restart())
                        .show();
                alertDialog.create();
            }
            else if (guess.equals(Integer.toString(rightNbr)) && lives >= 1) {
                AlertDialog.Builder winDialog = new AlertDialog.Builder(this);
                winDialog
                        .setTitle("You Won!")
                        .setMessage(String.format("Our guess was %s.\nWould you like to play again?", guess))
                        .setCancelable(false)
                        .setNegativeButton("No", (dialog, which) -> dialog.cancel())
                        .setPositiveButton("Yes", (dialog, which) -> restart())
                        .show();
                winDialog.create();
            }
            else {
                restart();
            }
        });

    }

    public void restart() {
        Intent intentToRestart = new Intent(GamePlay.this, ChooseDigits.class);
        startActivity(intentToRestart);
        finish();
    }

}
