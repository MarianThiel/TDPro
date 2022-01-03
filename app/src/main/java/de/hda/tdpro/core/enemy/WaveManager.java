package de.hda.tdpro.core.enemy;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

import de.hda.tdpro.core.Drawable;

/**
 * @author Marian Thiel
 * @version 1.0
 *
 * Class for managing Waves
 * holds all waves of an entire level
 */
public class WaveManager implements Drawable {
    /**
     * number of waves
     */
    private final int NUMBER_OF_WAVES;
    /**
     * Array of Waves
     */
    private final EnemyWave[] waves;
    /**
     * passed by path
     */
    private final Path path;

    /**
     * integer for current wave have to be consistent while wave is running
     */
    private int currentWave;

    private int lastWaveInserted;

    /**
     * test constructor
     * @param NUMBER_OF_WAVES number of enemies
     * @param path path
     */
    public WaveManager(int NUMBER_OF_WAVES, Path path) {
        this.NUMBER_OF_WAVES = NUMBER_OF_WAVES;
        waves = new EnemyWave[NUMBER_OF_WAVES];
        currentWave = 0;
        lastWaveInserted = 0;
        this.path = path;
    }



    /**
     * create waves for demonstration purpose
     * 10000 Enemies for performance testing
     */
    public void initDemoData(){
        for(int i = 0; i < NUMBER_OF_WAVES;i++){
            waves[i] = new EnemyWave(10000, path);
            waves[i].initDemoEnemies();
        }
    }

    /**
     * starts the current wave
     */
    public void startCurrentWave(){

        waves[currentWave].startWave();
        //currentWave++;
    }

    public void addAll(List<EnemyWave> waves){
        for (EnemyWave w : waves){
            if(lastWaveInserted < NUMBER_OF_WAVES)
            this.waves[lastWaveInserted++] = w;
        }
    }

    /**
     * get the Enemies of the current wave as a list
     * @return a list of Enemies from the current wave
     */
    public List<Enemy> getEnemiesOfCurrentWave(){
        List<Enemy> lst = new LinkedList<>();
        for(int i = 0; i < waves[currentWave].getENEMIES_IN_WAVE(); i++){
            if(waves[currentWave].getEnemy(i) != null){
                lst.add(waves[currentWave].getEnemy(i));
            }
        }
        return lst;
    }

    public boolean isCurrentWaveFinished(){
       for (int i = 0; i < waves[currentWave].getENEMIES_IN_WAVE(); i++){
           Log.println(Log.ASSERT,"WAVE","");
           Enemy e = waves[currentWave].getEnemy(i);

           if(e != null){
               Log.println(Log.ASSERT,"WAVE",i+ ": " + e.isFinished());
               if(!e.isFinished()){
                   return false;
               }
           }
       }
       return true;
    }

    public void prepare(){
        currentWave++;
    }

    public int getNUMBER_OF_WAVES() {
        return NUMBER_OF_WAVES;
    }

    public int getCurrentWave() {
        return currentWave;
    }

    @Override
    public void draw(Canvas canvas) {
        waves[currentWave].draw(canvas);
    }
}
