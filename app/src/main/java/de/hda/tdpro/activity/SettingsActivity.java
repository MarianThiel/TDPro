package de.hda.tdpro.activity;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;


import de.hda.tdpro.R;
import de.hda.tdpro.core.config.SettingSaving;

public class SettingsActivity extends AppCompatActivity {
    private SeekBar seekbar;
    private AudioManager audioManager;
    private CheckBox contrast;
    private ImageButton returnFromSetting;
    private SeekBar seekbar2;
    private TextView sound;
    private TextView textSize;
    private TextView contrastText;




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_settings);
        seekbar= findViewById(R.id.seekBar);
        returnFromSetting= findViewById(R.id.returnFromSetting);
        contrast = findViewById(R.id.contrastButton);
        seekbar2= findViewById(R.id.seekBar2);
        //audioManager= (AudioManager)getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
        audioManager= (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        sound = findViewById(R.id.textView);
        textSize= findViewById(R.id.textView5);
        contrastText= findViewById(R.id.textView2);


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
                openMainMenu();

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
        float f = sound.getTextSize();
        seekbar2.setMax(30);
        seekbar2.setMin(15);
        seekbar2.setProgress((int)sound.getTextSize());
        seekbar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                sound.setTextSize(TypedValue.COMPLEX_UNIT_SP,i);
                textSize.setTextSize(TypedValue.COMPLEX_UNIT_SP,i);
                contrastText.setTextSize(TypedValue.COMPLEX_UNIT_SP,i);
                SettingSaving.setTextSize(i);


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }



    private void openMainMenu() {
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
    }
}