package com.example.theripper.game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        DisplayMetrics displayMetrics=new DisplayMetrics();

        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        Constants.SCREEN_HEIGHT=displayMetrics.heightPixels;
        Constants.SCREEN_WIDTH=displayMetrics.widthPixels;

        System.out.println(Constants.SCREEN_HEIGHT);
        System.out.println(Constants.SCREEN_WIDTH);
        GamePanel gamePanel=new GamePanel(this);

        setContentView(gamePanel);
    }
}
