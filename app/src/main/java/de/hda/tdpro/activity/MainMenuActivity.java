package de.hda.tdpro.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

import de.hda.tdpro.ConfigWriter;
import de.hda.tdpro.MusicPlayer;
import de.hda.tdpro.R;
import de.hda.tdpro.StaticContext;
import pl.droidsonroids.gif.GifImageButton;

public class MainMenuActivity extends AppCompatActivity implements View.OnClickListener {

    private MusicPlayer musicPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        musicPlayer = MusicPlayer.getInstance();
        musicPlayer.loadMusic(this, R.raw.ingame);
        musicPlayer.startMusic();



        StaticContext.setContext(this);

        ConfigWriter.getInstance().readDiamonds();


        ConfigWriter.getInstance().readDiamonds();

        GifImageButton levelSelect = (GifImageButton) findViewById(R.id.buttonLevelSelection);
        levelSelect.setOnClickListener((View.OnClickListener) this);
        GifImageButton spellSelect = (GifImageButton) findViewById(R.id.buttonSpellSelection);
        spellSelect.setOnClickListener((View.OnClickListener) this);
        GifImageButton towerSelect = (GifImageButton) findViewById(R.id.buttonTowerSelection);
        towerSelect.setOnClickListener((View.OnClickListener) this);
        ImageButton lexikonSelect = (ImageButton) findViewById(R.id.imageButtonLexikon);
        lexikonSelect.setOnClickListener((View.OnClickListener) this);
        ImageButton settingsSelect = (ImageButton) findViewById(R.id.imageButtonSettings);
        settingsSelect.setOnClickListener((View.OnClickListener) this);

    }

    public void onClick(View view) {
        GifImageButton levelSelect = findViewById(R.id.buttonLevelSelection);
        GifImageButton spellSelect = findViewById(R.id.buttonSpellSelection);
        GifImageButton towerSelect = findViewById(R.id.buttonTowerSelection);
        ImageButton settingsSelect = findViewById(R.id.imageButtonSettings);
        ImageButton lexikonSelect = findViewById(R.id.imageButtonLexikon);

        if (view == levelSelect) {
            Intent fp = new Intent(getApplicationContext(), LevelSelectionActivity.class);
            startActivity(fp);
        }
        if (view == spellSelect) {
            Intent fp = new Intent(getApplicationContext(), UpgradeAbility.class);
            startActivity(fp);
        }
        if (view == towerSelect) {
            Intent fp = new Intent(getApplicationContext(), UpgradeTower.class);
            startActivity(fp);
        }
        if (view == settingsSelect) {
            Intent fp = new Intent(getApplicationContext(), SettingsActivity.class);
            startActivity(fp);
        }
        if (view == lexikonSelect) {
            Intent fp = new Intent(getApplicationContext(), LexiconActivity.class);
            startActivity(fp);
        }

    }
}

