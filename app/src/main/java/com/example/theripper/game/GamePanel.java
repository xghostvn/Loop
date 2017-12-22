package com.example.theripper.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.constraint.solver.widgets.Rectangle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by The Ripper on 12/9/2017.
 */

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

   private MainThread thread;
   private SenceManager senceManager;

    public GamePanel(Context context) {
        super(context);
        getHolder().addCallback(this);
        Constants.CURRENNT_CONTEXT=context;
        thread=new MainThread(getHolder(),this);
        senceManager=new SenceManager();

        setFocusable(true);


    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(Color.WHITE);
        senceManager.draw(canvas);

    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
            thread = new MainThread(getHolder(),this);
            Constants.INIT_TIME=System.currentTimeMillis();
            thread.setrunning(true);
            thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        boolean retry=true;
        while (retry){
            try {
                thread.setrunning(true);
                thread.start();
            }
            catch (Exception e) {e.printStackTrace();}
            retry=false;
        }

    }

    public void update()
    {

        senceManager.update();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        senceManager.reviceTouch(event);
        return  true;
    }




}
