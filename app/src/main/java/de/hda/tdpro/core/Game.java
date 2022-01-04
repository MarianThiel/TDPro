package de.hda.tdpro.core;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import de.hda.tdpro.R;
import de.hda.tdpro.StaticContext;
import de.hda.tdpro.core.enemy.Enemy;
import de.hda.tdpro.core.enemy.Path;
import de.hda.tdpro.core.enemy.WaveManager;
import de.hda.tdpro.core.tower.Tower;
import de.hda.tdpro.core.tower.TowerManager;
import de.hda.tdpro.core.tower.TowerType;
import de.hda.tdpro.core.tower.upgrades.MetaUpgrade;


/**
 * Game controls all towers and waves. it draws the entire screen
 */
public class Game implements Drawable, GameObservable, EnemyObserver {
    /**
     * control waves
     */
    private WaveManager waveManager;
    /**
     * control towers
     */
    private TowerManager towerManager;

    private int health;

    private int gold;

    private Path path;

    private PointingMode pointingMode;

    private Tower selectedTower;

    private final List<GameListener> listeners;

    private final Map<String, MetaUpgrade> upgrades;

    private final Bitmap bg;

    private boolean prepared;


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
        bg = BitmapFactory.decodeResource(StaticContext.getContext().getResources(), R.drawable.grass_template2);
        runningWave = false;
        prepared = false;
        health = 100000; // test
        gold = 200; // test
        //initDemoData();
    }

    private void initDemoData() {


        addUpgrade(new MetaUpgrade("DMG-1",10,0,0,20));
        addUpgrade(new MetaUpgrade("VEL-1",0,10,0,20));
        addUpgrade(new MetaUpgrade("RAD-1",0,00,10,20));



        path = new Path();
        path.addStaticPoint(0,0);
        path.addStaticPoint(50,300);
        path.addStaticPoint(300,500);
        path.addStaticPoint(500,550);
        path.addStaticPoint(500,650);
        path.addStaticPoint(1000,750);
        path.addStaticPoint(1000,450);
        path.addStaticPoint(500,450);
        path.addStaticPoint(500,250);
        path.addStaticPoint(1500,250);
        path.addStaticPoint(1500,1250);
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
            e.addEnemyObserver(this);
            towerManager.addTowerAsListener(e);
        }
    }

    /**
     * starts the next wave
     * @return true if possible, false if wave is currently running
     */
    public boolean startNextWave(){
        if(!runningWave){
            Log.println(Log.ASSERT,"WAVE","WAVE STARTED");
            runningWave = true;
            if(!prepared){
                prepareNextWave();
                prepared = true;
            }

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
        Tower t = towerManager.placeTower(type,new Position(x,y));
        if(t != null)
            addTowerAsListener(t);
        return t != null;
    }

    private void addTowerAsListener(Tower t){
        List<Enemy> l = waveManager.getEnemiesOfCurrentWave();

        for(Enemy e : l){
            e.addEnemyObserver(t);
        }
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

    public int getHealth() {
        return health;
    }

    public synchronized void setHealth(int health) {

        this.health = health;
        if(health<=0){
            notifyOnGameOver();
        }
    }

    public void setRunningWave(boolean runningWave) {
        this.runningWave = runningWave;
    }


    public int getMaxWaves(){
        return waveManager.getNUMBER_OF_WAVES();
    }
    public int getCurrentWave(){
        return waveManager.getCurrentWave()+1;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bg,0,0,null);
        path.draw(canvas);
        waveManager.draw(canvas);
        towerManager.draw(canvas);
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

    @Override
    public void notifyOnGameOver() {
        for(GameListener l : listeners){
            l.updateOnGameOver();
        }
    }

    @Override
    public void notifyOnChange() {
        for(GameListener l : listeners){
            l.updateOnChange();
        }
    }

    @Override
    public void notifyOnGameWinning() {
        for(GameListener l : listeners){
            l.updateOnGameWinning();
        }
    }

    @Override
    public void onEnemyMovement(Enemy e) {

    }

    @Override
    public void onEnemySuccess(Enemy e) {
        setHealth(getHealth() - e.getHp());
        prepareOnEvent();
        notifyOnChange();
        //Log.println(Log.ASSERT,"WAVE","WAVE STARTED");
    }


    @Override
    public void onEnemyDying(Enemy e) {
        prepareOnEvent();
        notifyOnChange();
        //Log.println(Log.ASSERT,"WAVE","WAVE STARTED");
    }

    private void prepareOnEvent() {
        if (waveManager.isCurrentWaveFinished()) {
            if(waveManager.getCurrentWave()-1 == waveManager.getNUMBER_OF_WAVES())
                return;
            runningWave = false;
            waveManager.prepare();
            prepareNextWave();
            prepared = true;



        }
    }

    public void pause(){
        waveManager.pause();

    }

    public void resume(){
        waveManager.resume();
    }
}
