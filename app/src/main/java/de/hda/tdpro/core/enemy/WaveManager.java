package de.hda.tdpro.core.enemy;

import android.content.Context;
import android.graphics.Canvas;

import java.util.LinkedList;
import java.util.List;

import de.hda.tdpro.core.Drawable;

public class WaveManager implements Drawable {

    private final int NUMBER_OF_WAVES;

    private final EnemyWave[] waves;

    private final Path path;

    private Context context;

    private final int currentWave;

    public WaveManager(int NUMBER_OF_WAVES, Path path) {
        this.NUMBER_OF_WAVES = NUMBER_OF_WAVES;
        waves = new EnemyWave[NUMBER_OF_WAVES];
        currentWave = 0;
        this.path = path;
    }

    public WaveManager(int NUMBER_OF_WAVES, Path path, Context context) {
        this.NUMBER_OF_WAVES = NUMBER_OF_WAVES;
        waves = new EnemyWave[NUMBER_OF_WAVES];
        currentWave = 0;
        this.path = path;
        this.context = context;
    }

    public void initDemoData(){
        for(int i = 0; i < NUMBER_OF_WAVES;i++){
            waves[i] = new EnemyWave(10000, path, context);
            waves[i].initDemoEnemies();
        }
    }

    public void startCurrentWave(){

        waves[currentWave].startWave();

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

    @Override
    public void draw(Canvas canvas) {
        waves[currentWave].draw(canvas);
    }
}
