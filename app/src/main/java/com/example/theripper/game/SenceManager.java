package com.example.theripper.game;

import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.ArrayList;

/**
 * Created by The Ripper on 12/13/2017.
 */

public class SenceManager {

    private ArrayList<Sence> sences=new ArrayList<>();
    public static int ACTIVE_SENCE;


    public SenceManager(){
        ACTIVE_SENCE=0;
        sences.add(new GamePlaySence());
    }


    public void draw(Canvas canvas){
         sences.get(ACTIVE_SENCE).draw(canvas);
    }

    public void update(){
            sences.get(ACTIVE_SENCE).update();
    }

    public void reviceTouch(MotionEvent event){
        sences.get(ACTIVE_SENCE).reviceTouch(event);
    }



}
