package de.hda.tdpro.core;

import android.content.Context;


import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import de.hda.tdpro.R;
import de.hda.tdpro.core.enemy.Enemy;
import de.hda.tdpro.core.enemy.EnemyWave;
import de.hda.tdpro.core.enemy.Path;
import de.hda.tdpro.core.enemy.WaveManager;
import de.hda.tdpro.core.tower.TowerManager;
import de.hda.tdpro.core.tower.TowerType;


/**
 * Game controls all towers and waves. it draws the entire screen
 */
public class Game implements Drawable{
    /**
     * control waves
     */
    private WaveManager waveManager;
    /**
     * control towers
     */
    private TowerManager towerManager;

    private EnemyWave wave;

    private Path path;

    /**
     * basic constructor for Game class
     * @param context necessary for accessing resources
     */
    public Game(Context context){
        towerManager = new TowerManager(3, context);
        towerManager.placeTower(TowerType.FIRE_TOWER,new Position(400,400));
        towerManager.placeTower(TowerType.FIRE_TOWER,new Position(800,400));
        towerManager.placeTower(TowerType.FIRE_TOWER,new Position(600,600));

        path = new Path();
        path.addPoint(0,0);
        path.addPoint(50,300);
        path.addPoint(300,500);
        path.addPoint(500,550);
        path.addPoint(500,650);
        path.addPoint(1000,750);
        path.addPoint(1000,450);
        path.addPoint(500,450);
        path.addPoint(500,250);
        path.addPoint(1500,250);
        path.addPoint(1500,1250);
        wave = new EnemyWave(9,path);

        Enemy e1 = new Enemy(2000,1,20, BitmapFactory.decodeResource(context.getResources(), R.drawable.test3));
        Enemy e2 = new Enemy(200,1,60, BitmapFactory.decodeResource(context.getResources(), R.drawable.test3));
        Enemy e3 = new Enemy(200,1,70, BitmapFactory.decodeResource(context.getResources(), R.drawable.test3));
        Enemy e4 = new Enemy(500,1,100, BitmapFactory.decodeResource(context.getResources(), R.drawable.test3));
        Enemy e5 = new Enemy(500,1,100, BitmapFactory.decodeResource(context.getResources(), R.drawable.test3));
        Enemy e6 = new Enemy(500,1,200, BitmapFactory.decodeResource(context.getResources(), R.drawable.test3));
        Enemy e7 = new Enemy(500,1,200, BitmapFactory.decodeResource(context.getResources(), R.drawable.test3));
        Enemy e8 = new Enemy(500,1,200, BitmapFactory.decodeResource(context.getResources(), R.drawable.test3));
        Enemy e9 = new Enemy(500,1,200, BitmapFactory.decodeResource(context.getResources(), R.drawable.test3));

        e1.addEnemyObserver(towerManager.getTower(0));
        e2.addEnemyObserver(towerManager.getTower(0));
        e3.addEnemyObserver(towerManager.getTower(0));
        e4.addEnemyObserver(towerManager.getTower(0));
        e5.addEnemyObserver(towerManager.getTower(0));
        e6.addEnemyObserver(towerManager.getTower(0));
        e7.addEnemyObserver(towerManager.getTower(0));
        e8.addEnemyObserver(towerManager.getTower(0));
        e9.addEnemyObserver(towerManager.getTower(0));

        e1.addEnemyObserver(towerManager.getTower(1));
        e2.addEnemyObserver(towerManager.getTower(1));
        e3.addEnemyObserver(towerManager.getTower(1));
        e4.addEnemyObserver(towerManager.getTower(1));
        e5.addEnemyObserver(towerManager.getTower(1));
        e6.addEnemyObserver(towerManager.getTower(1));
        e7.addEnemyObserver(towerManager.getTower(1));
        e8.addEnemyObserver(towerManager.getTower(1));
        e9.addEnemyObserver(towerManager.getTower(1));

        e1.addEnemyObserver(towerManager.getTower(2));
        e2.addEnemyObserver(towerManager.getTower(2));
        e3.addEnemyObserver(towerManager.getTower(2));
        e4.addEnemyObserver(towerManager.getTower(2));
        e5.addEnemyObserver(towerManager.getTower(2));
        e6.addEnemyObserver(towerManager.getTower(2));
        e7.addEnemyObserver(towerManager.getTower(2));
        e8.addEnemyObserver(towerManager.getTower(2));
        e9.addEnemyObserver(towerManager.getTower(2));

        wave.addEnemy(e1);
        wave.addEnemy(e2);
        wave.addEnemy(e3);
        wave.addEnemy(e4);
        wave.addEnemy(e5);
        wave.addEnemy(e6);
        wave.addEnemy(e7);
        wave.addEnemy(e8);
        wave.addEnemy(e9);




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
        wave.startWave();
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

    @Override
    public void draw(Canvas canvas) {
        path.draw(canvas);
        wave.draw(canvas);
        towerManager.getTower(0).draw(canvas);
        towerManager.getTower(1).draw(canvas);
        towerManager.getTower(2).draw(canvas);
    }
}
