package de.hda.tdpro;

import android.content.Context;
import android.media.MediaPlayer;

public class MusicPlayer {

    private static MusicPlayer instance;

    private MediaPlayer mediaPlayer;

    private float currentVolume;

    private boolean playingMusic;

    private MusicPlayer(){

        currentVolume = 0.5f;
        playingMusic = false;
    }

    public static MusicPlayer getInstance() {
        if(instance == null ) instance = new MusicPlayer();
        return instance;
    }

    public void setVolume(float vol){
        currentVolume = vol;
        mediaPlayer.setVolume(currentVolume,currentVolume);
    }

    public void mute(boolean value){
        if(value)
            mediaPlayer.setVolume(0,0);
        else
            setVolume(currentVolume);
    }

    public void loadMusic(Context c, int id){
        mediaPlayer = MediaPlayer.create(c, id);
        mediaPlayer.setLooping(true);
        setVolume(currentVolume);
    }

    public void startMusic(){
        if(!playingMusic) {
            mediaPlayer.start();
            playingMusic = true;
        }
    }

    public void pauseMusic(){
        if(playingMusic){
            mediaPlayer.pause();
            playingMusic = false;
        }

    }

    public void stopMusic(){
        if(playingMusic) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }

    public boolean isPlayingMusic() {
        return playingMusic;
    }
}
