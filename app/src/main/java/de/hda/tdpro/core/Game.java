package de.hda.tdpro.core;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;

import de.hda.tdpro.R;
import de.hda.tdpro.core.enemy.Enemy;
import de.hda.tdpro.core.enemy.Path;

public class Game implements Drawable{

    Enemy e;
    Enemy e2;
    Context context;
    public Game(Context context) {
        Bitmap img = BitmapFactory.decodeResource(context.getResources(), R.drawable.test3);
        if(img == null){
            Log.println(Log.ASSERT,"test","img.toString()");
        }else{
            Log.println(Log.ASSERT,"test","!img.toString()");
        }

        e = new Enemy(1000,1,100,img);
        e2 = new Enemy(1000,1,100,img);

        Path path = new Path();
        path.addPoint(10,10);
        path.addPoint(100,100);
        path.addPoint(100,150);
        path.addPoint(300,150);
        path.addPoint(300,1500);

        Path path2 = new Path();
        path.addPoint(15,15);
        path.addPoint(100,150);
        path.addPoint(130,150);
        path.addPoint(360,150);
        path.addPoint(300,1500);
        e2.setWalkingPath(path2);
        e.setWalkingPath(path);


        this.context = context;

    }

    public void start(){
        e.start();
        e2.start();
    }

    @Override
    public void draw(Canvas canvas) {
        e.draw(canvas);
        e2.draw(canvas);
    }
}
