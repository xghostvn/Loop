package com.example.theripper.game;

import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * Created by The Ripper on 12/13/2017.
 */

interface  Sence  {
    public void draw(Canvas canvas);
    public void update();
    public void teminal();
    public void reviceTouch(MotionEvent event);

}
