package de.hda.tdpro.core;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceView;

public class DemoView extends SurfaceView implements Runnable{

    Thread thread;
    Game game;
    public DemoView(Context context) {
        super(context);
    }

    public DemoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DemoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private void init(Context context){
        game = new Game(context);
    }
    @Override
    public void run() {

    }
}
