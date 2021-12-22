package de.hda.tdpro.core;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import de.hda.tdpro.R;
import de.hda.tdpro.core.enemy.Enemy;
import de.hda.tdpro.core.enemy.EnemyWave;
import de.hda.tdpro.core.enemy.Path;

public class Game implements Drawable{

    EnemyWave wave;
    Context context;

    public Game(Context context) {
        this.context = context;
        initWave();
    }

    private void initWave(){
        Path path = new Path();
        path.addPoint(0,0);
        path.addPoint(10,10);
        path.addPoint(10,20);
        path.addPoint(400,300);
        path.addPoint(200,300);
        path.addPoint(600,300);
        path.addPoint(100,300);

        wave = new EnemyWave(10,path);

        Enemy e1 = new Enemy(100,1,50, BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher_foreground));
        Enemy e2 = new Enemy(200,1,50, BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher_foreground));
        Enemy e3 = new Enemy(300,1,50, BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher_foreground));
        Enemy e4 = new Enemy(400,1,50, BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher_foreground));

        wave.addEnemy(e1);
        wave.addEnemy(e2);
        wave.addEnemy(e3);
        wave.addEnemy(e4);
    }

    public void startDemo(){
        wave.startWave();
    }

    @Override
    public void draw(Canvas canvas) {
        wave.draw(canvas);
    }
}
