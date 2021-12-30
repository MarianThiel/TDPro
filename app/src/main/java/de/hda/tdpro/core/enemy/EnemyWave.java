package de.hda.tdpro.core.enemy;

import android.content.Context;
import android.graphics.Canvas;

import java.util.List;

import de.hda.tdpro.core.Drawable;
import de.hda.tdpro.core.Position;
import de.hda.tdpro.core.factories.EnemyFactory;

public class EnemyWave implements Runnable, Drawable {

    private final int ENEMIES_IN_WAVE;

    private final Enemy[] enemies;

    private Thread thread;

    private Context context;

    private int lastEnemyPosition;

    private final Path mainPath;

    private final List<Position> positions;

    public EnemyWave(int ENEMIES_IN_WAVE, Path path) {
        this.ENEMIES_IN_WAVE = ENEMIES_IN_WAVE;
        enemies = new Enemy[ENEMIES_IN_WAVE];
        mainPath = path;
        lastEnemyPosition = 0;
        positions = path.generateAllPositions();
    }

    public EnemyWave(int ENEMIES_IN_WAVE, Path path, Context context) {
        this.ENEMIES_IN_WAVE = ENEMIES_IN_WAVE;
        enemies = new Enemy[ENEMIES_IN_WAVE];
        mainPath = path;
        lastEnemyPosition = 0;
        this.context = context;
        positions = path.generateAllPositions();
    }


    public void addEnemy(Enemy e){
        e.setWalkingPath(positions);
        enemies[lastEnemyPosition++] = e;
    }
    public void initDemoEnemies(){
        for (int i = 0; i < ENEMIES_IN_WAVE; i++){
            enemies[i] = EnemyFactory.getInstance(context.getResources()).createRandomEnemy();
            enemies[i].setWalkingPath(positions);
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
