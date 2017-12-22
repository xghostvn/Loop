package com.example.theripper.game;

import android.graphics.Canvas;

import java.util.ArrayList;

/**
 * Created by The Ripper on 12/11/2017.
 */

public class ObstacleManager {

    private ArrayList<Obstacle> obstacles;
    private int obstaclesGap;
    private int playerGap;
    private int obstaclesHeight;
    private int color;

    private long startTime;
    private long initTime;

    public ObstacleManager(int obstaclesGap,int playerGap,int obstaclesHeight,int color){
            this.obstaclesGap=obstaclesGap;
            this.playerGap=playerGap;
            this.obstaclesHeight=obstaclesHeight;
            this.color=color;
            obstacles=new ArrayList<>();

            startTime=initTime=System.currentTimeMillis();

            populateObstacles();

    }

    private void populateObstacles() {

        int currY=-5*Constants.SCREEN_HEIGHT/4;


        while (currY<0){
                    int startX= (int) (Math.random()*(Constants.SCREEN_WIDTH-playerGap));
                    obstacles.add(new Obstacle(obstaclesHeight,startX,currY,color,playerGap));
                    currY+=obstaclesHeight+obstaclesGap;

        }


    }




    public boolean PlayerColliside(RectPlayer rectPlayer){
        for(Obstacle ob : obstacles)
            if(ob.PlayerColliside(rectPlayer)) return true;


        return false;
    }


    public void draẉ̣̣̣̣̣̣̣̣̣̣̣̣(Canvas canvas){
        for(Obstacle ob : obstacles)
            ob.draw(canvas);

    }

    public void update(){
        if(startTime<Constants.INIT_TIME)
            startTime=Constants.INIT_TIME;

        int elapsedTime= (int) (System.currentTimeMillis()-startTime);
        startTime=System.currentTimeMillis();
        float speed= (float) (Math.sqrt(1+(startTime-initTime)/2000 ) *  Constants.SCREEN_HEIGHT/10000.0f);
        for(Obstacle ob : obstacles){
            ob.incrementY(speed*elapsedTime);
        }

        if(obstacles.get(obstacles.size()-1).getRectangle().top >= Constants.SCREEN_HEIGHT){

            System.out.println(obstacles.size());

            int startX= (int) (Math.random()*(Constants.SCREEN_WIDTH-playerGap));
            obstacles.add(0,new Obstacle(obstaclesHeight,startX,obstacles.get(0).getRectangle().top-obstaclesHeight-obstaclesGap,color,playerGap));
            obstacles.remove(obstacles.size()-1);
        }
    }


}
