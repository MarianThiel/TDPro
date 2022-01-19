package de.hda.tdpro.core;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import java.util.ArrayList;
import java.util.List;

import de.hda.tdpro.ConfigWriter;
import de.hda.tdpro.R;

import de.hda.tdpro.StaticContext;

import de.hda.tdpro.core.enemy.Enemy;
import de.hda.tdpro.core.enemy.Path;
import de.hda.tdpro.core.enemy.WaveManager;
import de.hda.tdpro.core.tower.Tower;
import de.hda.tdpro.core.tower.TowerManager;
import de.hda.tdpro.core.tower.TowerType;
import de.hda.tdpro.core.tower.upgrades.MetaTower;
import de.hda.tdpro.core.tower.upgrades.UpgradeType;
import de.hda.tdpro.core.tower.upgrades.MetaUpgrade;


/**
 * @author Marian Thiel
 * Game controls all towers and waves. delegate draw method to every drawable instance
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
    /**
     * hp of the player
     */
    private int health;
    /**
     * amount of gold
     */
    private int gold;
    /**
     * main path of the Game
     */
    private Path path;

    private int diamonds;



    private Tower selectedTower;

    private final List<GameListener> listeners;

    private final Bitmap bg;

    private boolean prepared;

    private boolean runningWave;





    /**
     * basic constructor for Game class
     * @param waveManager r
     */
    public Game(WaveManager waveManager, Path path, int health, int gold, int diamonds){


        listeners = new ArrayList<>();

        towerManager = new TowerManager(10);
        this.path = path;
        this.waveManager = waveManager;

        Bitmap b = BitmapFactory.decodeResource(StaticContext.getContext().getResources(), R.drawable.b2);
        WindowManager wm = ((WindowManager) StaticContext.getContext().getSystemService(Context.WINDOW_SERVICE));
        DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);

        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        bg = Bitmap.createScaledBitmap(b,height,width,false);

        runningWave = false;
        prepared = false;
        this.health = health;
        this.gold = gold;
        this.diamonds = diamonds;
    }


    /**
     * selects a tower on specific position
     * notifies each observer that selection was initiated
     * @param x value on x-axis
     * @param y value on y-axis
     */
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
                prepareNextWave(); // will only be called after the initial nextWave call
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
        int price;
        price = MetaTower.getMetaTower(type).getPrice();
        if(price <= getGold()){
            setGold(getGold()-price);

            Tower t = towerManager.placeTower(type,new Position(x,y));
            if(t != null)
                addTowerAsListener(t);
            notifyOnTowerPlacement();
            return t != null;
        }

        return false;
    }

    /**
     * method handles the upgrade process of the selected tower
     * @param type Enum which determines the upgrade type
     * @return true if upgrade wa successful, false if not
     */
    public boolean upgradeSelectedTower(UpgradeType type){
        MetaUpgrade meta = MetaUpgrade.getMetaUpgrade(type);
        boolean possible = false;
        if(meta.getPRICE() <= getGold()) {
            if(possible = towerManager.upgradeTower(selectedTower, meta)){
                setGold(getGold() - meta.getPRICE());
                selectTower(selectedTower.getPos().getxVal(), selectedTower.getPos().getyVal());
            }
        }
        return possible;
    }

    /**
     * method to add a tower as an EnemyListener to each Enemy in current wave
     * @param t tower to add as an observer
     */
    private void addTowerAsListener(Tower t){
        List<Enemy> l = waveManager.getEnemiesOfCurrentWave();
        for(Enemy e : l){
            e.addEnemyObserver(t);
        }
    }


    public Tower getSelectedTower() {
        return selectedTower;
    }

    public boolean isRunningWave() {
        return runningWave;
    }


    public int getHealth() {
        return health;
    }

    /**
     * sets the hp of game
     * notifies its observer when game is over
     * @param health hp to set
     */
    public synchronized void setHealth(int health) {

        this.health = health;
        notifyOnChange();
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
        notifyOnChange();
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
        // may be used for debug purposes
    }

    /**
     * reduces the hp by the remaining hp of the succeeded enemy
     * @param e observed enemy
     */
    @Override
    public void onEnemySuccess(Enemy e) {
        setHealth(getHealth() - e.getHp());
        prepareOnEvent();


    }

    /**
     * increases gold when enemy died
     * @param e e
     */
    @Override
    public void onEnemyDying(Enemy e) {
        setGold(getGold() + e.getGoldDropping());
        prepareOnEvent();


    }

    /**
     * method is called if an enemy dies or succeeded
     * checks if the current wave was finished and sets winning and loosing condition
     */
    private void prepareOnEvent() {
        if (waveManager.isCurrentWaveFinished()) {
            Log.println(Log.ASSERT,"DIAMONDS_WON",  "DIAMONDS_WON:" + waveManager.getDiamondsOfCurrentWave());
            if(waveManager.getCurrentWave() + 1 == waveManager.getNUMBER_OF_WAVES()){
                if(health <= 0){
                    notifyOnGameOver();
                }else
                    notifyOnGameWinning();
                return;
            }
            this.diamonds = waveManager.getDiamondsOfCurrentWave();
            ConfigWriter.getInstance().writeDiamonds(diamonds);
            runningWave = false;
            waveManager.prepare();
            prepareNextWave();
            prepared = true;
        }
    }

    /**
     * delegates pause to waveManager and towerManager
     */
    public void pause(){
        waveManager.pause();
        towerManager.pauseTowers();

    }

    /**
     * delegates resume to waveManager and towerManager
     */
    public void resume(){
        waveManager.resume();
        towerManager.resumeTowers();
    }
}
