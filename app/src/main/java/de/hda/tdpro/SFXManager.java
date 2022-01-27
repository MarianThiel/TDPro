package de.hda.tdpro;

import android.media.MediaPlayer;

public class SFXManager {

    private MediaPlayer shootSFX;
    private MediaPlayer nextWave;
    private MediaPlayer checkpointReached;
    private MediaPlayer enemyDies;

    private static SFXManager instance;



    private SFXManager() {
        nextWave = MediaPlayer.create(StaticContext.getContext(),R.raw.start_wave);
        enemyDies = MediaPlayer.create(StaticContext.getContext(),R.raw.deathd);
        checkpointReached = MediaPlayer.create(StaticContext.getContext(),R.raw.chkpt);
    }

    public void playNextWave(){
        nextWave.start();
    }

    public void playEnemyDies(){
        if(enemyDies.isPlaying()){
            enemyDies.stop();
        }
        enemyDies.start();
    }

    public void playCheckpointReached(){
        checkpointReached.start();
    }

    public static SFXManager getInstance() {
        if (instance == null) instance = new SFXManager();
        return instance;
    }
}
