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


    private Path path;

    private PointingMode pointingMode;

    private Tower selectedTower;

    private final List<GameListener> listeners;

    private final Map<String, MetaUpgrade> upgrades;



    private boolean runningWave;

    /**
     * basic constructor for Game class
     * @param waveManager r
     */
    public Game(WaveManager waveManager, Path path){

        pointingMode = PointingMode.SELECTION_MODE;
        listeners = new ArrayList<>();
        upgrades = new HashMap<>();
        towerManager = new TowerManager(5);
        this.path = path;
        this.waveManager = waveManager;

        runningWave = false;
        //initDemoData();
    }

    private void initDemoData() {


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
        //wave = new EnemyWave(13,path);
        waveManager = new WaveManager(1,path);
        waveManager.initDemoData();
        towerManager = new TowerManager(10);
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
        List<Enemy> lst = waveManager.getEnemiesOfCurrentWave();
        for(Enemy e : lst){
            towerManager.addTowerAsListener(e);
        }
    }

    /**
     * starts the next wave
     * @return true if possible, false if wave is currently running
     */
    public boolean startNextWave(){
        if(!runningWave){
            runningWave = true;
            prepareNextWave();
            waveManager.startCurrentWave();

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
        waveManager.draw(canvas);
        /*
        towerManager.getTower(0).draw(canvas);
        towerManager.getTower(1).draw(canvas);
        towerManager.getTower(2).draw(canvas);
        towerManager.getTower(4).draw(canvas);
        towerManager.getTower(5).draw(canvas);
        towerManager.getTower(6).draw(canvas);
        towerManager.getTower(7).draw(canvas);
        if(towerManager.getTower(3)!=null){
            towerManager.getTower(3).draw(canvas);
        }*/
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
