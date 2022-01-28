package de.hda.tdpro.activity;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.SeekBar;


import de.hda.tdpro.R;
import de.hda.tdpro.view.ExampleDialog;

public class SettingsActivity extends AppCompatActivity {
    private SeekBar seekbar;
    private AudioManager audioManager;
    private CheckBox muted;
    private ImageButton returnFromSetting;
    private Button reset;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_settings);

        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

        seekbar= findViewById(R.id.seekBar);
        returnFromSetting= findViewById(R.id.returnFromSetting);
        muted = (CheckBox) findViewById(R.id.contrastButton);
        //audioManager= (AudioManager)getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
        audioManager= (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        reset= findViewById(R.id.buttonReset);




        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            setAllButton();
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void setAllButton(){

        seekbar.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        seekbar.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));
        returnFromSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean inGame = getIntent().getBooleanExtra("INGAME", false);

                if(inGame){
                    backToInGame();
                }else{
                    openMainMenu();
                }
            }
        });
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,i ,0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        muted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(muted.isChecked()) {
                    //audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                    audioManager.adjustStreamVolume(
                            AudioManager.STREAM_VOICE_CALL, AudioManager.ADJUST_MUTE, /* flags= */ 0);
                }
                else{
                    //audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                    //audioManager.adjustVolume();
                    audioManager.adjustStreamVolume(
                            AudioManager.STREAM_VOICE_CALL, AudioManager.ADJUST_UNMUTE, /* flags= */ 0);
                }
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();


            }
        });



    }
    public void openDialog(){
        ExampleDialog ex = new ExampleDialog();
        ex.show(getSupportFragmentManager(),"Example Dialog");
    }



    private void openMainMenu() {
        Intent intent = new Intent(SettingsActivity.this, MainMenuActivity.class);
        startActivity(intent);
    }

    private void backToInGame(){
        Intent intent = new Intent(SettingsActivity.this, InGameActivity.class);
        startActivity(intent);
    }
}