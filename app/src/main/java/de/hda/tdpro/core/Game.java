package de.hda.tdpro.core;

import android.content.Context;
<<<<<<< HEAD
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import de.hda.tdpro.R;
import de.hda.tdpro.core.enemy.Enemy;
import de.hda.tdpro.core.enemy.EnemyWave;
import de.hda.tdpro.core.enemy.Path;
=======

import de.hda.tdpro.core.enemy.WaveManager;
>>>>>>> Branch_Marian
import de.hda.tdpro.core.tower.Tower;
import de.hda.tdpro.core.tower.TowerManager;
import de.hda.tdpro.core.tower.TowerType;

<<<<<<< HEAD
public class Game implements Drawable{

    EnemyWave wave;
    Context context;
    TowerManager towerManager;

    public Game(Context context) {
        this.context = context;

        towerManager = new TowerManager(1);
        towerManager.placeTower(0, TowerType.FIRE_TOWER);
        towerManager.getTower(0).setPos(new Position(400,400));
        initWave();
    }

    private void initWave(){
        Path path = new Path();
        path.addPoint(0,0);
        path.addPoint(10,10);
        path.addPoint(10,100);
        path.addPoint(100,300);
        path.addPoint(200,300);
        path.addPoint(600,300);
        path.addPoint(200,300);

        wave = new EnemyWave(10,path);

        Enemy e1 = new Enemy(100,1,50, BitmapFactory.decodeResource(context.getResources(), R.drawable.test3));
        Enemy e2 = new Enemy(200,1,50, BitmapFactory.decodeResource(context.getResources(), R.drawable.test3));
        Enemy e3 = new Enemy(300,1,300, BitmapFactory.decodeResource(context.getResources(), R.drawable.test3));
        Enemy e4 = new Enemy(400,1,300, BitmapFactory.decodeResource(context.getResources(), R.drawable.test3));

        e1.addEnemyObserver(towerManager.getTower(0));
        e2.addEnemyObserver(towerManager.getTower(0));
        e3.addEnemyObserver(towerManager.getTower(0));
        e4.addEnemyObserver(towerManager.getTower(0));

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
        towerManager.getTower(0).draw(canvas);
    }
=======
/**
 * Game controls all towers and waves. it draws the entire screen
 */
public class Game {
    /**
     * control waves
     */
    private WaveManager waveManager;
    /**
     * control towers
     */
    private TowerManager towerManager;

    /**
     * basic constructor for Game class
     * @param context
     */
    public Game(Context context){

    }

    /**
     * connects all towers as observer to each enemy for the next wave
     */
    private void prepareNextWave(){

    }

    /**
     * starts the next wave
     * @return true if possible, false if wave is currently running
     */
    public boolean startNextWave(){
        return true;
    }

    /**
     *
     * @param type the tower type to build
     * @param x coordinate on game board
     * @param y coordinate on game board
     * @return true if max tower not reached, false if max tower reached
     */
    public boolean placeTowerAt(TowerType type, int x, int y){
        return towerManager.placeTower(type,new Position(x,y));
    }

>>>>>>> Branch_Marian
}
