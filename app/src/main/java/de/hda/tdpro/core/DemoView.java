package de.hda.tdpro.core;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import de.hda.tdpro.R;
import de.hda.tdpro.core.tower.Tower;

public class DemoView extends SurfaceView implements Runnable, GameListener{

    volatile boolean playing;

    private Thread gameThread;

    private SurfaceHolder surfaceHolder;

    private Canvas canvas;

    public Game game;



    public DemoView(Context context) {
        super(context);
        init(context);
    }

    public DemoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DemoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    void init(Context context){


        surfaceHolder = getHolder();



        this.setOnTouchListener((view, motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                switch (game.getPointingMode()){
                    case SELECTION_MODE:
                        handleSelection((int)motionEvent.getX(),(int)motionEvent.getY());
                        break;
                }
            }
            return true;
        });
    }

    private void handleSelection(int x, int y){
       game.selectTower(x, y);

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

    }

    public void setGameModel(Game g){
        this.game = g;
    }



    @Override
    public void run() {
        while(playing){
            draw();
            control();
        }
    }


    @Override
    public void updateOnSelection() {

    }
}
