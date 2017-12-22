package com.example.theripper.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.provider.SyncStateContract;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by The Ripper on 12/13/2017.
 */

public class GamePlaySence implements Sence{

    private RectPlayer rectPlayer;
    private Rect r= new Rect();
    private Point playerpoint;
    private ObstacleManager obstacleManager;
    private boolean gameOver=false;
    private boolean movePlayer=false;
    private long gameOverTime;

    private OrientationData orientationData;
    private long frameTime;


    public GamePlaySence(){
        rectPlayer=new RectPlayer(new Rect(100,100,175,175), Color.rgb(255,0,0));
        playerpoint=new Point(Constants.SCREEN_WIDTH/2,3*Constants.SCREEN_HEIGHT/4);

        obstacleManager=new ObstacleManager(450 ,300,75,Color.BLACK);


        orientationData=new OrientationData();
        orientationData.register();
        frameTime=System.currentTimeMillis();
    }
    @Override
    public void draw(Canvas canvas) {
        rectPlayer.draw(canvas);
        obstacleManager.draẉ̣̣̣̣̣̣̣̣̣̣̣̣(canvas);


        if(gameOver){
            Paint paint=new Paint();
            paint.setTextSize(100);
            paint.setColor(Color.MAGENTA);

            drawTextGO(canvas,paint,"Game Over " );



        }
    }
    private void reset() {
        playerpoint=new Point(Constants.SCREEN_WIDTH/2,3*Constants.SCREEN_HEIGHT/4);
        obstacleManager=new ObstacleManager(450 ,300,75,Color.BLACK);
        movePlayer=false;
        gameOver=false;
        rectPlayer.update(playerpoint);

    }


    @Override
    public void update() {
        if(!gameOver){
            if(frameTime<Constants.INIT_TIME)
                frameTime=Constants.INIT_TIME;

            int elapsedTime=(int) (System.currentTimeMillis()-frameTime);
            frameTime=System.currentTimeMillis();



            if(orientationData.getOrientation() != null && orientationData.getStartOrientation() != null) {
                float pitch = orientationData.getOrientation()[1] - orientationData.getStartOrientation()[1];
                float roll = orientationData.getOrientation()[2] - orientationData.getStartOrientation()[2];

                float xSpeed = 2* roll * Constants.SCREEN_WIDTH/1000f;
                float ySpeed = pitch * Constants.SCREEN_HEIGHT/1000f;




                playerpoint.x += Math.abs(xSpeed*elapsedTime) > 5 ? xSpeed*elapsedTime : 0;
                playerpoint.y -= Math.abs(ySpeed*elapsedTime) > 5 ? ySpeed*elapsedTime : 0;
            }
            Log.d("x", "playerx" +playerpoint.x);
            Log.d("x", "playerx" +playerpoint.y);
            if (playerpoint.x<0)
                playerpoint.x=0;
            else if(playerpoint.x > Constants.SCREEN_WIDTH)
                playerpoint.x =Constants.SCREEN_WIDTH;
            else if(playerpoint.y<0)
                playerpoint.y=0;
            else if(playerpoint.y > 3*Constants.SCREEN_HEIGHT/4)
                playerpoint.y = 3*Constants.SCREEN_HEIGHT/4;



            rectPlayer.update(playerpoint);
            obstacleManager.update();

            if(obstacleManager.PlayerColliside(rectPlayer))
                gameOver=true;
        }

    }

    @Override
    public void teminal() {
        SenceManager.ACTIVE_SENCE=0;
    }

    @Override
    public void reviceTouch(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if(!gameOver && rectPlayer.getRectangle().contains((int) event.getX(),(int) event.getY()))
                    movePlayer=true;

                if(gameOver){
                    reset();

                    orientationData.newGame();
                }


                break;
            case MotionEvent.ACTION_MOVE:
                if(!gameOver && movePlayer)
                    playerpoint.set((int) event.getX(), (int) event.getY());
                break;
            case  MotionEvent.ACTION_UP:
                movePlayer=false;
                break;
        }
    }


    private void drawTextGO(Canvas canvas, Paint paint, String text) {
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.getClipBounds(r);
        int cHeight = r.height();
        int cWidth = r.width();
        paint.getTextBounds(text, 0, text.length(), r);
        float x = cWidth / 2f - r.width() / 2f - r.left;
        float y = cHeight / 2f + r.height() / 2f - r.bottom;
        canvas.drawText(text, x, y, paint);

    }
}
