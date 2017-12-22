package com.example.theripper.game;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by The Ripper on 12/9/2017.
 */

public class MainThread extends Thread {

    public boolean running;
    private SurfaceHolder surfaceHolder;
    private GamePanel gamePanel;
    private static final int MAX_FPS =40;
    private double avergeFPS;
    private Canvas canvas;
    public MainThread(SurfaceHolder surfaceHolder,GamePanel gamePanel) {
                this.surfaceHolder=surfaceHolder;
                this.gamePanel=gamePanel;
    }


    public void setrunning(boolean running)
    {
               this.running=running;
    }

    @Override
    public void run() {


        long starttime;
        long waittime;
        long timemilis=1000/MAX_FPS;
        long totaltime=0;
        long targettime=1000/MAX_FPS;
        int framecount=0;


        while (running)
        {
            starttime=System.nanoTime();

            canvas=null;

            try {
                canvas=surfaceHolder.lockCanvas();
                synchronized (surfaceHolder)
                {
                    this.gamePanel.update();
                    this.gamePanel.draw(canvas);
                }
            }catch (Exception e) {e.printStackTrace();}
            finally {
                try {
                    if(canvas!=null)
                        surfaceHolder.unlockCanvasAndPost(canvas);
                }
                catch (Exception e) {e.printStackTrace();}
            }

            timemilis=(System.nanoTime()-starttime)/1000000;
            waittime=targettime-timemilis;

            try {
                if(waittime>0)
                    sleep(waittime);
            }catch (Exception e) {e.printStackTrace();}

            totaltime+=System.nanoTime()-starttime;
            framecount++;

            if(framecount==MAX_FPS)
            {
                avergeFPS=1000/((totaltime/framecount)/1000000);
                framecount=0;
                totaltime=0;

            }

        }


    }
}
