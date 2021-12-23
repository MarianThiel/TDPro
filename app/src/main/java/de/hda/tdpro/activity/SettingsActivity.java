package de.hda.tdpro.activity;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Toast;


import de.hda.tdpro.R;

public class SettingsActivity extends AppCompatActivity {
    private SeekBar seekbar;
    private AudioManager audioManager;
    private Button plusSound;
    private Button minusSound;
    private Button plusContrast;
    private Button minusContrast;
    private ImageButton returnFromSetting;
    private SeekBar seekbar2;
    private int brightness;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        seekbar= findViewById(R.id.seekBar);
        plusContrast= findViewById(R.id.plusContrast);
        minusContrast= findViewById(R.id.minusContrast);
        plusSound= findViewById(R.id.plusSound);
        minusSound = findViewById(R.id.minusSound);
        returnFromSetting= findViewById(R.id.returnFromSetting);
        seekbar2= findViewById(R.id.seekBar2);
        //audioManager= (AudioManager)getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
        audioManager= (AudioManager)getSystemService(Context.AUDIO_SERVICE);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            setAllButton();
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void setAllButton(){

        seekbar.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        seekbar.setProgress(audioManager.getStreamMinVolume(AudioManager.STREAM_MUSIC));
        plusSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //audioManager.adjustVolume(AudioManager.ADJUST_LOWER,AudioManager.FLAG_PLAY_SOUND);
                audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC,
                        AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI);
                seekbar.setProgress(audioManager.getStreamMinVolume(AudioManager.STREAM_MUSIC));
            }
        });
        minusSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //audioManager.adjustVolume(AudioManager.ADJUST_LOWER,AudioManager.FLAG_PLAY_SOUND);
                audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC,
                        AudioManager.ADJUST_LOWER, AudioManager.FLAG_SHOW_UI);
                seekbar.setProgress(audioManager.getStreamMinVolume(AudioManager.STREAM_MUSIC));
            }
        });
        plusContrast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        minusContrast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        returnFromSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainMenu();

            }
        });
        // Set Brightness of Screen
        seekbar2.setMax(255);
        seekbar2.setKeyProgressIncrement(1);
        try{
            brightness= Settings.System.getInt(getContentResolver(),Settings.System.SCREEN_BRIGHTNESS);
        } catch (Settings.SettingNotFoundException e) {
            Log.e("Error","can not access brightness");
            e.printStackTrace();
        }
        seekbar2.setProgress(brightness);
        seekbar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                {
                    //Set the minimal brightness level
                    //if seek bar is 20 or any value below
                    if(i<=20)
                    {
                        //Set the brightness to 20
                        brightness=20;
                    }
                    else //brightness is greater than 20
                    {
                        //Set brightness variable based on the progress bar
                        brightness = i;
                    }
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //Set the system brightness using the brightness variable value
                Settings.System.putInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, brightness);
                //Get the current window attributes
                WindowManager.LayoutParams layoutpars = getWindow().getAttributes();
                //Set the brightness of this window
                layoutpars.screenBrightness = brightness / (float)255;
                //Apply attribute changes to this window
                getWindow().setAttributes(layoutpars);
            }
        });

    }



    private void openMainMenu() {
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
    }
}