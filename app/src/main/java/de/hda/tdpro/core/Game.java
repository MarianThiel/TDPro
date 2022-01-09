package de.hda.tdpro.core;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

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
import de.hda.tdpro.core.tower.UpgradeType;
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
        towerManager = new TowerManager(10);
        this.path = path;
        this.waveManager = waveManager;
        Bitmap b = BitmapFactory.decodeResource(StaticContext.getContext().getResources(), R.drawable.b2);
        WindowManager wm = ((WindowManager) StaticContext.getContext().getSystemService(Context.WINDOW_SERVICE));
        DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);

        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        bg = Bitmap.createScaledBitmap(b,width,height,false);

        runningWave = false;
        prepared = false;
        health = 1000000; // test
        gold = 200; // test
        //initDemoData();


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
        notifyOnTowerPlacement();
        return t != null;
    }

    public boolean upgradeSelectedTower(UpgradeType type){
        return true;
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

    public synchronized void setGold(int gold) {
        this.gold = gold;

    }

    @Override
    public void draw(Canvas canvas) {

        canvas.drawBitmap(bg,0,0,null);
        path.draw(canvas);
        waveManager.draw(canvas);
        towerManager.draw(canvas);

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
    public void notifyOnTowerPlacement() {
        for(GameListener l : listeners){
            l.updateOnTowerPlacement();
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
        setGold(getGold() + e.getGoldDropping());
        prepareOnEvent();
        notifyOnChange();



    }

    private void prepareOnEvent() {
        if (waveManager.isCurrentWaveFinished()) {
            if(waveManager.getCurrentWave() + 1 == waveManager.getNUMBER_OF_WAVES()){
                if(health <= 0){
                    notifyOnGameOver();
                }else
                    notifyOnGameWinning();
                return;
            }

            runningWave = false;
            waveManager.prepare();
            prepareNextWave();
            prepared = true;



        }
    }

    public void pause(){
        waveManager.pause();
        towerManager.pauseTowers();

    }

    public void resume(){
        waveManager.resume();
        towerManager.resumeTowers();
    }
}
