package com.example.theripper.game;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Rect;

/**
 * Created by The Ripper on 12/10/2017.
 */

public class RectPlayer implements Object{

    private Rect rectangle;
    private int color;

    private Animations idle;
    private Animations walkRight;
    private Animations walkleft;

    private AnimationManager animationManager;

    public RectPlayer(Rect rectangle,int color)
    {
        this.rectangle=rectangle;
        this.color=color;

        BitmapFactory bf=new BitmapFactory();
        Bitmap idleImg=bf.decodeResource(Constants.CURRENNT_CONTEXT.getResources(),R.drawable.alienblue);
        Bitmap walk1=bf.decodeResource(Constants.CURRENNT_CONTEXT.getResources(),R.drawable.alienblue_walk1);
        Bitmap walk2=bf.decodeResource(Constants.CURRENNT_CONTEXT.getResources(),R.drawable.alienblue_walk2);

        idle=new Animations(new Bitmap[]{idleImg},2);
        walkRight=new Animations(new Bitmap[]{walk1,walk2}, (0.5f));

        Matrix matrix=new Matrix();
        matrix.preScale(-1,1);
        walk1=Bitmap.createBitmap(walk1,0,0,walk1.getWidth(),walk1.getHeight(),matrix,false);
        walk2=Bitmap.createBitmap(walk2,0,0,walk2.getWidth(),walk2.getHeight(),matrix,false);
        walkleft=new Animations(new Bitmap[]{walk1,walk2}, (0.5f));

        animationManager = new AnimationManager(new Animations[]{idle,walkleft,walkRight});
    }


    public Rect getRectangle() {
        return rectangle;
    }

    @Override
    public void draw(Canvas canvas) {
      //  Paint paint=new Paint();
      //  paint.setColor(color);
        //canvas.drawRect(rectangle,paint);

        animationManager.draw(canvas,rectangle);


    }

    @Override
    public void update() {
        animationManager.update();
    }


    public void update(Point point)
    {
        float oldleft=rectangle.left;

        rectangle.set(point.x-rectangle.width()/2,point.y-rectangle.height()/2,point.x+rectangle.width()/2,point.y+rectangle.height()/2);

        int state=1;
        if(rectangle.left-oldleft>5)
            state=1;
        else if(rectangle.left-oldleft<-5)
            state=2;

        animationManager.playAnim(state);
        animationManager.update();

    }



}
