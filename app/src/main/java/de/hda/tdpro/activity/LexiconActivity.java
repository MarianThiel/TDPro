package de.hda.tdpro.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import de.hda.tdpro.R;

public class LexiconActivity extends AppCompatActivity {

    private ImageButton btnReturn;
    private WebView displayText;
    private LinearLayout buttonLayout;

    private Button gameplay;
    private Button upgrades;
    private Button towers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_lexicon);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        btnReturn = findViewById(R.id.img_btn_return);
        displayText = findViewById(R.id.web_display);
        buttonLayout = findViewById(R.id.buttonLayout);

        gameplay = findViewById(R.id.btnGameplay);
        towers = findViewById(R.id.btnTowerLex);
        upgrades = findViewById(R.id.btnUpgradeLex);

        gameplay.setOnClickListener(e->{
            displayText.loadUrl("file:///android_asset/lexicon/Gameplay.html");
            select(gameplay);
        });
        upgrades.setOnClickListener(e->{
            displayText.loadUrl("file:///android_asset/lexicon/upgrades.html");
            select(upgrades);
        });
        towers.setOnClickListener(e->{
            displayText.loadUrl("file:///android_asset/lexicon/tower.html");
            select(towers);
        });

        //initLexicon();

        btnReturn.setOnClickListener(e->{
            boolean inGame = getIntent().getBooleanExtra("INGAME", false);
            if(inGame){
                Intent intent = new Intent(LexiconActivity.this, InGameActivity.class);
                startActivity(intent);
            }else{
                Intent intent = new Intent(LexiconActivity.this, MainMenuActivity.class);
                startActivity(intent);
            }

        });

    }

    private void select(Button b){
        gameplay.setActivated(false);
        towers.setActivated(false);
        upgrades.setActivated(false);
        b.setActivated(true);
    }

    /**
     * inits the vertical layout with buttons for each lexicon section
     * each button Listener loads an html file in the main web view
     */
    private void initLexicon(){
        Button btn = new Button(this);
        btn.setText("Spiel");
        btn.setOnClickListener(e->{
            displayText.loadUrl("file:///android_asset/lexicon/Gameplay.html");
        });
        buttonLayout.addView(btn);

        btn = new Button(this);
        btn.setText("Türme");
        btn.setOnClickListener(e->{
            displayText.loadUrl("file:///android_asset/lexicon/tower.html");
        });
        buttonLayout.addView(btn);

        btn = new Button(this);
        btn.setText("Upgrades");
        btn.setOnClickListener(e->{
            displayText.loadUrl("file:///android_asset/lexicon/upgrades.html");
        });
        buttonLayout.addView(btn);

        btn = new Button(this);

        btn.setText("Fähigkeiten");

        btn.setOnClickListener(e->{
            displayText.loadUrl("file:///android_asset/lexicon/ability.html");
        });
        buttonLayout.addView(btn);

    }



}