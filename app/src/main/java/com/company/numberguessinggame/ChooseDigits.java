package com.company.numberguessinggame;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class ChooseDigits extends AppCompatActivity {
    RadioGroup items;
    RadioButton two;
    RadioButton three;
    RadioButton four;
    Button start;
    int rightNbr;
    final RandomNbrGenerator number = new RandomNbrGenerator();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_digits);

        items = findViewById(R.id.group);
        two = findViewById(R.id.twoDigits);
        three = findViewById(R.id.threeDigits);
        four = findViewById(R.id.fourDigits);
        start = findViewById(R.id.buttonStart);

        start.setOnClickListener(v -> {
            if (two.isChecked()) rightNbr = number.generateRandomInt(100);
            else if (three.isChecked()) rightNbr = number.generateRandomInt(1000);
            else if (four.isChecked()) rightNbr = number.generateRandomInt(10_000);

            Intent intentToPlay = new Intent(ChooseDigits.this, GamePlay.class);
            intentToPlay.putExtra("rightNbr", rightNbr);
            startActivity(intentToPlay);
            finish();
        });
    }
}
