package com.example.theripper.game;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.constraint.solver.widgets.Rectangle;

/**
 * Created by The Ripper on 12/10/2017.
 */

public class Obstacle implements Object{


    private Rect rectangle;
    private Rect rectangle2;
    private int color;


    public  Obstacle(int rectangleHeight,int startX,int startY,int color,int playerGap)
    {
        //l,t,r,b
        rectangle= new Rect(0,startY,startX,startY+rectangleHeight);
        rectangle2=new Rect(startX+playerGap,startY,Constants.SCREEN_WIDTH,startY+rectangleHeight);
        this.color=color;
    }

    public Rect getRectangle() {
        return rectangle;
    }



    public boolean PlayerColliside(RectPlayer Rectplayer){
        return (Rect.intersects(Rectplayer.getRectangle(),rectangle ) || Rect.intersects(Rectplayer.getRectangle(),rectangle2));
    }

    public void incrementY(float y){
        rectangle.top+=y;
        rectangle.bottom+=y;
        rectangle2.top+=y;
        rectangle2.bottom+=y;
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint=new Paint();
        paint.setColor(color);
            canvas.drawRect(rectangle,paint);
            canvas.drawRect(rectangle2,paint);
    }

    @Override
    public void update() {

    }
}
