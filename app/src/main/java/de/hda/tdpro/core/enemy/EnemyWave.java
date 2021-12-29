package de.hda.tdpro.core.enemy;

import android.content.res.Resources;
import android.graphics.Canvas;

import de.hda.tdpro.core.Drawable;
import de.hda.tdpro.core.factories.EnemyFactory;

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
    public void initDemoEnemies(){
        for (int i = 0; i < ENEMIES_IN_WAVE; i++){
            enemies[i] = EnemyFactory.getInstance(Resources.getSystem()).createRandomEnemy();
        }
    }

    public void startWave(){
        thread = new Thread(this);
        thread.start();
    }

    public Enemy getEnemy(int i){
        return enemies[i];
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

    public int getENEMIES_IN_WAVE() {
        return ENEMIES_IN_WAVE;
    }

    @Override
    public void draw(Canvas canvas) {
        for(Enemy e : enemies){
            if(e!=null)
            e.draw(canvas);
        }
    }
}
