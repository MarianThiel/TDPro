package de.hda.tdpro.core.enemy;

import android.graphics.Canvas;

import de.hda.tdpro.core.Drawable;

public class EnemyWave implements Runnable, Drawable {

    private final int ENEMIES_IN_WAVE;

    private final Enemy[] enemies;

    private Thread thread;

    private int lastEnemyPosition;
    private final Path mainPath;

    public EnemyWave(int ENEMIES_IN_WAVE, Path path) {
        this.ENEMIES_IN_WAVE = ENEMIES_IN_WAVE;
        enemies = new Enemy[ENEMIES_IN_WAVE];
        mainPath = path;
        lastEnemyPosition = 0;
    }

    public void addEnemy(Enemy e){
        e.setWalkingPath(mainPath);
        enemies[lastEnemyPosition++] = e;
    }

    public void startWave(){
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        for(Enemy e : enemies){
            if(e!=null)
            e.initWalking();
            try {
                Thread.sleep((long) (Math.random() * 1000));
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
        for(Enemy e : enemies){
            if(e!=null)
            e.draw(canvas);
        }
    }
}
