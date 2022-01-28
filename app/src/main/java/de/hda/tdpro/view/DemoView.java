package de.hda.tdpro.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

import de.hda.tdpro.StaticContext;
import de.hda.tdpro.core.Game;
import de.hda.tdpro.core.GameListener;
import de.hda.tdpro.core.PointingMode;
import de.hda.tdpro.core.path.Position;
import de.hda.tdpro.core.tower.TowerType;

public class DemoView extends SurfaceView implements Runnable, GameListener {

    volatile boolean playing;

    private Thread gameThread;

    private SurfaceHolder surfaceHolder;

    private Canvas canvas;

    public Game game;

    private PointingMode mode;

    private int RENDER_TIME;

    private boolean placingTower;

    private TowerType placingTowerType;

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
        mode = PointingMode.SELECTION_MODE;
        RENDER_TIME = 1;
        surfaceHolder = getHolder();
        placingTower = false;


        this.setOnTouchListener((view, motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                if(game.intersects(new Position((int)motionEvent.getX(),(int)motionEvent.getY()))){
                    Log.println(Log.ASSERT,"INTERSECTED", "TRUE");

                }else{
                    Log.println(Log.ASSERT,"INTERSECTED", "FALSE");
                }

                switch (mode){
                    case SELECTION_MODE:
                        handleSelection((int)motionEvent.getX(),(int)motionEvent.getY());
                        break;
                    case PLACE_TOWER_MODE:
                        if(!game.intersects(new Position((int)motionEvent.getX(),(int)motionEvent.getY()))) {
                            handleTowerPlacement(placingTowerType, (int) motionEvent.getX(), (int) motionEvent.getY());
                            setMode(PointingMode.SELECTION_MODE);
                        }
                        break;
                }
            }
            return true;
        });
    }

    private void handleSelection(int x, int y){
       game.selectTower(x, y);

    }

    private  void handleTowerPlacement(TowerType type, int x, int y){
        if(!game.placeTowerAt(type, x, y)){
            setMode(PointingMode.SELECTION_MODE);
        }
    }

    private void draw(){
        synchronized (surfaceHolder){
            if (surfaceHolder.getSurface().isValid()) {
                //locking the canvas
                canvas = surfaceHolder.lockCanvas();
                //drawing a background color for canvas
                //canvas.drawColor(Color.WHITE);
                //Drawing the player
                game.draw(canvas);

                if(placingTower){
                    WindowManager wm = ((WindowManager) StaticContext.getContext().getSystemService(Context.WINDOW_SERVICE));
                    DisplayMetrics metrics = new DisplayMetrics();
                    wm.getDefaultDisplay().getMetrics(metrics);

                    int height = metrics.heightPixels;
                    int width = metrics.widthPixels;
                    if(height>width){
                        int t = width;
                        width = height;
                        height = t;
                    }
                    Paint p = new Paint();
                    p.setStyle(Paint.Style.FILL_AND_STROKE);
                    p.setColor(Color.parseColor("#212121"));
                    p.setStrokeWidth(10);
                    p.setAlpha(80);
                    Rect r = new Rect();
                    r.set(0,0,width,height);
                    canvas.drawRect(r,p);

                }
                //Unlocking the canvas
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }

    }

    private void control(){
        try {
            Thread.sleep(RENDER_TIME);
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
        game.pause();
    }

    public void resume() {
        //when the game is resumed
        //starting the thread again
        RENDER_TIME = 10;
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
        //game.resume();
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

    @Override
    public void updateOnGameOver() {

    }

    @Override
    public void updateOnChange() {

    }

    @Override
    public void updateOnGameWinning() {

    }

    @Override
    public void updateOnTowerPlacement() {

    }

    @Override
    public void updateOnCheckpoint() {

    }

    public void setMode(PointingMode mode){
        this.mode = mode;

        switch (mode){
            case PLACE_TOWER_MODE:
                placingTower = true;
                break;
            case SELECTION_MODE:
                placingTower = false;
                break;
        }
    }

    public void setPlacingTowerType(TowerType placingTowerType) {
        this.placingTowerType = placingTowerType;
    }
}
