package de.hda.tdpro.core;

import android.content.Context;

import de.hda.tdpro.core.enemy.WaveManager;
import de.hda.tdpro.core.tower.Tower;
import de.hda.tdpro.core.tower.TowerManager;
import de.hda.tdpro.core.tower.TowerType;

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

}
