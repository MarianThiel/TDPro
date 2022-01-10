package de.hda.tdpro.core.enemy;

import android.content.Context;
import android.graphics.Canvas;

import java.util.List;

import de.hda.tdpro.core.Drawable;
import de.hda.tdpro.core.Position;
import de.hda.tdpro.core.factories.EnemyFactory;

public class EnemyWave implements Runnable, Drawable {
    /**
     * constant for enemies in a single wave
     */
    private final int ENEMIES_IN_WAVE;
    /**
     * Array of Enemies
     */
    private final Enemy[] enemies;
    /**
     * thread for ejection enemies in wave
     */
    private Thread thread;

    /**
     * last position in Array for insert
     * note: inconsistent with generate Data
     */
    private int lastEnemyPosition;
    /**
     * main path of enemies
     */
    private final Path mainPath;
    /**
     * list of positions generated from path
     */
    private final List<Position> positions;

    private boolean stopped;
    /**
     * local state variable to hold the current enemy pointer
     */
    private int currentEnemy;

    /**
     * test constructor
     * @param ENEMIES_IN_WAVE number of enemies
     * @param path Path reference
     */
    public EnemyWave(int ENEMIES_IN_WAVE, Path path) {
        this.ENEMIES_IN_WAVE = ENEMIES_IN_WAVE;
        enemies = new Enemy[ENEMIES_IN_WAVE];
        mainPath = path;
        lastEnemyPosition = 0;
        positions = path.generateAllPositions();
        stopped = true;
        currentEnemy = 0;
    }



    /**
     *
     * @param e enemy to insert
     */
    public void addEnemy(Enemy e){
        if(lastEnemyPosition < ENEMIES_IN_WAVE){
            e.setWalkingPath(positions);
            enemies[lastEnemyPosition++] = e;
        }
    }

    /**
     * method for demonstration purposes
     * creates random enemies on each position
     */
    public void initDemoEnemies(){
        for (int i = 0; i < ENEMIES_IN_WAVE; i++){
            enemies[i] = EnemyFactory.getInstance().createRandomEnemy();
            enemies[i].setWalkingPath(positions);
        }
    }

    /**
     * starts the wave by starting thread
     */
    public void startWave(){
        stopped = false;
        thread = new Thread(this);
        thread.start();

    }

    /**
     * get the enemy at position in array
     * @param i index of Array
     * @return desired Enemy or null
     */
    public Enemy getEnemy(int i){
        return enemies[i];
    }
    public int getENEMIES_IN_WAVE() {
        return ENEMIES_IN_WAVE;
    }

    /**
     * ejects enemies in wave by time
     */
    @Override
    public void run() {

        for(int i = currentEnemy; (i < ENEMIES_IN_WAVE) && !stopped; i++){
            currentEnemy = i;
            if(enemies[i]!=null)
            enemies[i].initWalking();
            currentEnemy = i;
            try {
                Thread.sleep(400);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }
    }

    /**
     * draws each enemie in wave which is not null, alive and walking
     * @param canvas the canvas to paint
     */
    @Override
    public void draw(Canvas canvas) {
        for(Enemy e : enemies){
            if(e!=null && e.isAlive() && e.isWalking())
            e.draw(canvas);
        }
    }

    /**
     * stops the thread - pauses ejecting enemies
     */
    public void pauseWaveEjecting(){
        if(!stopped){
            stopped = true;
            thread.interrupt();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * starts the thread
     */
    public void resumeWaveEjecting(){
        if(stopped){
            thread = new Thread(this);
            stopped = false;
            thread.start();
        }

    }
}
