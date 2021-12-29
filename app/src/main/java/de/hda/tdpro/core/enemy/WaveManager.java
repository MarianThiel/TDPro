package de.hda.tdpro.core.enemy;

import java.util.LinkedList;
import java.util.List;

public class WaveManager {

    private final int NUMBER_OF_WAVES;

    private final EnemyWave[] waves;

    private final Path path;

    private int currentWave;

    public WaveManager(int NUMBER_OF_WAVES, Path path) {
        this.NUMBER_OF_WAVES = NUMBER_OF_WAVES;
        waves = new EnemyWave[NUMBER_OF_WAVES];
        currentWave = 0;
        this.path = path;
    }

    public void initDemoData(){
        for(int i = 0; i < NUMBER_OF_WAVES;i++){
            waves[i] = new EnemyWave(30, path);
            waves[i].initDemoEnemies();
        }
    }

    public void startCurrentWave(){
        waves[currentWave].startWave();
        currentWave++;
    }

    public List<Enemy> getEnemiesOfCurrentWave(){
        List<Enemy> lst = new LinkedList<>();
        for(int i = 0; i < waves[currentWave].getENEMIES_IN_WAVE(); i++){
            if(waves[currentWave].getEnemy(i) != null){
                lst.add(waves[currentWave].getEnemy(i));
            }
        }
        return lst;
    }
}
