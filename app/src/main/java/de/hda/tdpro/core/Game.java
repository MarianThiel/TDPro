package de.hda.tdpro.core;

import android.content.Context;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import de.hda.tdpro.GameObservable;
import de.hda.tdpro.R;
import de.hda.tdpro.core.enemy.Enemy;
import de.hda.tdpro.core.enemy.EnemyWave;
import de.hda.tdpro.core.enemy.Path;
import de.hda.tdpro.core.enemy.WaveManager;
import de.hda.tdpro.core.factories.EnemyFactory;
import de.hda.tdpro.core.tower.Tower;
import de.hda.tdpro.core.tower.TowerManager;
import de.hda.tdpro.core.tower.TowerType;
import de.hda.tdpro.core.tower.upgrades.MetaUpgrade;


/**
 * Game controls all towers and waves. it draws the entire screen
 */
public class Game implements Drawable, GameObservable {
    /**
     * control waves
     */
    private WaveManager waveManager;
    /**
     * control towers
     */
    private TowerManager towerManager;
    /**
     * just one wave for demonstration
     */
    private EnemyWave wave;

    private Path path;

    private PointingMode pointingMode;

    private Tower selectedTower;

    private final List<GameListener> listeners;

    private final Map<String, MetaUpgrade> upgrades;



    private boolean runningWave;

    /**
     * basic constructor for Game class
     * @param context necessary for accessing resources
     */
    public Game(Context context){

        pointingMode = PointingMode.SELECTION_MODE;
        listeners = new ArrayList<>();
        upgrades = new HashMap<>();

        runningWave = false;
        initDemoData(context);
    }

    private void initDemoData(Context context) {


        addUpgrade(new MetaUpgrade("DMG-1",10,0,0,20));
        addUpgrade(new MetaUpgrade("VEL-1",0,10,0,20));
        addUpgrade(new MetaUpgrade("RAD-1",0,00,10,20));



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
        wave = new EnemyWave(13,path);
        waveManager = new WaveManager(2,path);

        towerManager = new TowerManager(10, waveManager, context);
        towerManager.placeTower(TowerType.FIRE_TOWER,new Position(400,400));
        towerManager.placeTower(TowerType.FIRE_TOWER,new Position(800,400));
        towerManager.placeTower(TowerType.FIRE_TOWER,new Position(600,600));
        towerManager.placeTower(TowerType.FIRE_TOWER,new Position(500,500));
        towerManager.placeTower(TowerType.FIRE_TOWER,new Position(500,500));
        towerManager.placeTower(TowerType.FIRE_TOWER,new Position(500,500));
        towerManager.placeTower(TowerType.FIRE_TOWER,new Position(500,500));
        towerManager.placeTower(TowerType.FIRE_TOWER,new Position(500,500));
        towerManager.placeTower(TowerType.FIRE_TOWER,new Position(500,500));
        towerManager.placeTower(TowerType.FIRE_TOWER,new Position(500,500));

        Enemy e1 = EnemyFactory.getInstance(context.getResources()).createRandomEnemy();
        Enemy e2 = EnemyFactory.getInstance(context.getResources()).createRandomEnemy();
        Enemy e3 = EnemyFactory.getInstance(context.getResources()).createRandomEnemy();
        Enemy e4 = EnemyFactory.getInstance(context.getResources()).createRandomEnemy();
        Enemy e5 = EnemyFactory.getInstance(context.getResources()).createRandomEnemy();
        Enemy e6 = EnemyFactory.getInstance(context.getResources()).createRandomEnemy();
        Enemy e7 = EnemyFactory.getInstance(context.getResources()).createRandomEnemy();
        Enemy e8 = EnemyFactory.getInstance(context.getResources()).createRandomEnemy();
        Enemy e9 = EnemyFactory.getInstance(context.getResources()).createRandomEnemy();
        Enemy e10 = EnemyFactory.getInstance(context.getResources()).createRandomEnemy();
        Enemy e11 = EnemyFactory.getInstance(context.getResources()).createRandomEnemy();
        Enemy e12 = EnemyFactory.getInstance(context.getResources()).createRandomEnemy();
        Enemy e13 = EnemyFactory.getInstance(context.getResources()).createRandomEnemy();

        e1.addEnemyObserver(towerManager.getTower(0));
        e2.addEnemyObserver(towerManager.getTower(0));
        e3.addEnemyObserver(towerManager.getTower(0));
        e4.addEnemyObserver(towerManager.getTower(0));
        e5.addEnemyObserver(towerManager.getTower(0));
        e6.addEnemyObserver(towerManager.getTower(0));
        e7.addEnemyObserver(towerManager.getTower(0));
        e8.addEnemyObserver(towerManager.getTower(0));
        e9.addEnemyObserver(towerManager.getTower(0));
        e10.addEnemyObserver(towerManager.getTower(0));
        e11.addEnemyObserver(towerManager.getTower(0));
        e12.addEnemyObserver(towerManager.getTower(0));
        e13.addEnemyObserver(towerManager.getTower(0));

        e1.addEnemyObserver(towerManager.getTower(1));
        e2.addEnemyObserver(towerManager.getTower(1));
        e3.addEnemyObserver(towerManager.getTower(1));
        e4.addEnemyObserver(towerManager.getTower(1));
        e5.addEnemyObserver(towerManager.getTower(1));
        e6.addEnemyObserver(towerManager.getTower(1));
        e7.addEnemyObserver(towerManager.getTower(1));
        e8.addEnemyObserver(towerManager.getTower(1));
        e10.addEnemyObserver(towerManager.getTower(1));
        e11.addEnemyObserver(towerManager.getTower(1));
        e12.addEnemyObserver(towerManager.getTower(1));
        e13.addEnemyObserver(towerManager.getTower(1));

        e1.addEnemyObserver(towerManager.getTower(2));
        e2.addEnemyObserver(towerManager.getTower(2));
        e3.addEnemyObserver(towerManager.getTower(2));
        e4.addEnemyObserver(towerManager.getTower(2));
        e5.addEnemyObserver(towerManager.getTower(2));
        e6.addEnemyObserver(towerManager.getTower(2));
        e7.addEnemyObserver(towerManager.getTower(2));
        e8.addEnemyObserver(towerManager.getTower(2));
        e9.addEnemyObserver(towerManager.getTower(2));
        e10.addEnemyObserver(towerManager.getTower(2));
        e11.addEnemyObserver(towerManager.getTower(2));
        e12.addEnemyObserver(towerManager.getTower(2));
        e13.addEnemyObserver(towerManager.getTower(2));

        e1.addEnemyObserver(towerManager.getTower(3));
        e2.addEnemyObserver(towerManager.getTower(3));
        e3.addEnemyObserver(towerManager.getTower(3));
        e4.addEnemyObserver(towerManager.getTower(3));
        e5.addEnemyObserver(towerManager.getTower(3));
        e6.addEnemyObserver(towerManager.getTower(3));
        e7.addEnemyObserver(towerManager.getTower(3));
        e8.addEnemyObserver(towerManager.getTower(3));
        e9.addEnemyObserver(towerManager.getTower(3));
        e10.addEnemyObserver(towerManager.getTower(3));
        e11.addEnemyObserver(towerManager.getTower(3));
        e12.addEnemyObserver(towerManager.getTower(3));
        e13.addEnemyObserver(towerManager.getTower(3));

        e1.addEnemyObserver(towerManager.getTower(4));
        e2.addEnemyObserver(towerManager.getTower(4));
        e3.addEnemyObserver(towerManager.getTower(4));
        e4.addEnemyObserver(towerManager.getTower(4));
        e5.addEnemyObserver(towerManager.getTower(4));
        e6.addEnemyObserver(towerManager.getTower(4));
        e7.addEnemyObserver(towerManager.getTower(4));
        e8.addEnemyObserver(towerManager.getTower(4));
        e9.addEnemyObserver(towerManager.getTower(4));
        e10.addEnemyObserver(towerManager.getTower(4));
        e11.addEnemyObserver(towerManager.getTower(4));
        e12.addEnemyObserver(towerManager.getTower(4));
        e13.addEnemyObserver(towerManager.getTower(4));

        e1.addEnemyObserver(towerManager.getTower(5));
        e2.addEnemyObserver(towerManager.getTower(5));
        e3.addEnemyObserver(towerManager.getTower(5));
        e4.addEnemyObserver(towerManager.getTower(5));
        e5.addEnemyObserver(towerManager.getTower(5));
        e6.addEnemyObserver(towerManager.getTower(5));
        e7.addEnemyObserver(towerManager.getTower(5));
        e8.addEnemyObserver(towerManager.getTower(5));
        e9.addEnemyObserver(towerManager.getTower(5));
        e10.addEnemyObserver(towerManager.getTower(5));
        e11.addEnemyObserver(towerManager.getTower(5));
        e12.addEnemyObserver(towerManager.getTower(5));
        e13.addEnemyObserver(towerManager.getTower(5));

        e1.addEnemyObserver(towerManager.getTower(6));
        e2.addEnemyObserver(towerManager.getTower(6));
        e3.addEnemyObserver(towerManager.getTower(6));
        e4.addEnemyObserver(towerManager.getTower(6));
        e5.addEnemyObserver(towerManager.getTower(6));
        e6.addEnemyObserver(towerManager.getTower(6));
        e7.addEnemyObserver(towerManager.getTower(6));
        e8.addEnemyObserver(towerManager.getTower(6));
        e9.addEnemyObserver(towerManager.getTower(6));
        e10.addEnemyObserver(towerManager.getTower(6));
        e11.addEnemyObserver(towerManager.getTower(6));
        e12.addEnemyObserver(towerManager.getTower(6));
        e13.addEnemyObserver(towerManager.getTower(6));

        e1.addEnemyObserver(towerManager.getTower(7));
        e2.addEnemyObserver(towerManager.getTower(7));
        e3.addEnemyObserver(towerManager.getTower(7));
        e4.addEnemyObserver(towerManager.getTower(7));
        e5.addEnemyObserver(towerManager.getTower(7));
        e6.addEnemyObserver(towerManager.getTower(7));
        e7.addEnemyObserver(towerManager.getTower(7));
        e8.addEnemyObserver(towerManager.getTower(7));
        e9.addEnemyObserver(towerManager.getTower(7));
        e10.addEnemyObserver(towerManager.getTower(7));
        e11.addEnemyObserver(towerManager.getTower(7));
        e12.addEnemyObserver(towerManager.getTower(7));
        e13.addEnemyObserver(towerManager.getTower(7));

        wave.addEnemy(e1);
        wave.addEnemy(e2);
        wave.addEnemy(e3);
        wave.addEnemy(e4);
        wave.addEnemy(e5);
        wave.addEnemy(e6);
        wave.addEnemy(e7);
        wave.addEnemy(e8);
        wave.addEnemy(e9);
        wave.addEnemy(e10);
        wave.addEnemy(e11);
        wave.addEnemy(e12);
        wave.addEnemy(e13);
    }


    public void selectTower(int x, int y){
        towerManager.deselectAllTowers();
        Tower t = towerManager.getTowerByPosition(x, y);
        if(t != null){
            t.setActive(true);
        }
        selectedTower = t;
        notifyOnSelection();
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
        if(!runningWave){
            runningWave = true;
            wave.startWave();
            return true;
        }
        return false;
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

    public PointingMode getPointingMode() {
        return pointingMode;
    }

    public void setPointingMode(PointingMode pointingMode) {
        this.pointingMode = pointingMode;
    }

    public Tower getSelectedTower() {
        return selectedTower;
    }

    public boolean isRunningWave() {
        return runningWave;
    }

    public void addUpgrade(MetaUpgrade upgrade){
        upgrades.put(upgrade.getNAME(),upgrade);
    }

    public List<MetaUpgrade> getUpgrades(){
        return new LinkedList<>(upgrades.values());
    }

    @Override
    public void draw(Canvas canvas) {

        path.draw(canvas);
        wave.draw(canvas);
        towerManager.getTower(0).draw(canvas);
        towerManager.getTower(1).draw(canvas);
        towerManager.getTower(2).draw(canvas);
        towerManager.getTower(4).draw(canvas);
        towerManager.getTower(5).draw(canvas);
        towerManager.getTower(6).draw(canvas);
        towerManager.getTower(7).draw(canvas);
        if(towerManager.getTower(3)!=null){
            towerManager.getTower(3).draw(canvas);
        }
    }

    @Override
    public void addGameListener(GameListener listener) {
        listeners.add(listener);
    }

    @Override
    public void notifyOnSelection() {
        for(GameListener l : listeners){
            l.updateOnSelection();
        }
    }
}
