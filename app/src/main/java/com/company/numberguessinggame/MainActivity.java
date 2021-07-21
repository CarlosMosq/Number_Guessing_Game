package com.company.numberguessinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ImageView rotatingImage;
    TextView title;
    Animation animatedImage;
    Animation animatedText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rotatingImage = findViewById(R.id.rotatingImage);
        title = findViewById(R.id.appTitle);
        animatedImage = AnimationUtils.loadAnimation(this, R.anim.image_animation);
        animatedText = AnimationUtils.loadAnimation(this, R.anim.text_animation);
        rotatingImage.setAnimation(animatedImage);
        title.setAnimation(animatedText);

        new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                Intent intentToChoose = new Intent(MainActivity.this, ChooseDigits.class);
                startActivity(intentToChoose);
                finish();
            }
        }.start();

    }
}