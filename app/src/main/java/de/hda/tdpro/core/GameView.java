package de.hda.tdpro.core;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class GameView extends SurfaceView implements Runnable {

    volatile boolean playing;

    private Thread gameThread;

    private SurfaceHolder surfaceHolder;
    private Paint paint;
    private Canvas canvas;

    Game game;

    public GameView(Context context) {
        super(context);
        init(context);
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    void init(Context context){

        game = new Game(context);
        surfaceHolder = getHolder();
        paint = new Paint();
    }

   private void draw(){
       synchronized (surfaceHolder){
           if (surfaceHolder.getSurface().isValid()) {
               //locking the canvas
               canvas = surfaceHolder.lockCanvas();
               //drawing a background color for canvas
               canvas.drawColor(Color.WHITE);
               //Drawing the player
               game.draw(canvas);
               //Unlocking the canvas
               surfaceHolder.unlockCanvasAndPost(canvas);
           }
       }

    }

    private void control(){
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void pause() {
        //when the game is paused
        //setting the variable to false
        playing = false;
        try {
            //stopping the thread
            gameThread.join();
        } catch (InterruptedException e) {
        }
    }

    public void resume() {
        //when the game is resumed
        //starting the thread again
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
        game.start();
    }

    @Override
    public void run() {
        while(playing){
            draw();
            control();
        }
    }
}
