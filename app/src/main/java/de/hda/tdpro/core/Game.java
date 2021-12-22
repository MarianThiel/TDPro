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
        e2 = new Enemy(1000,1,1000,img);

        Path path = new Path();
        path.addPoint(15,15);
        path.addPoint(100,100);

        path.addPoint(100,150);
        path.addPoint(300,150);
        path.addPoint(300,1500);
        path.addPoint(100,100);

        Path path2 = new Path();
        path2.addPoint(15,10);
        path2.addPoint(100,150);
        path2.addPoint(130,150);
        path2.addPoint(30,150);
        path2.addPoint(350,200);
        e2.setWalkingPath(path);
        e.setWalkingPath(path);


        this.context = context;

    }

    public void start(){
        e.initWalking();
        e2.initWalking();
    }

    @Override
    public void draw(Canvas canvas) {
        e.draw(canvas);
        e2.draw(canvas);
    }
}
