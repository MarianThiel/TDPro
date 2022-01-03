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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_lexicon);

        btnReturn = findViewById(R.id.img_btn_return);
        displayText = findViewById(R.id.web_display);
        buttonLayout = findViewById(R.id.buttonLayout);

        initLexicon();

        btnReturn.setOnClickListener(e->{
            Intent intent = new Intent(LexiconActivity.this, MainMenuActivity.class);
            startActivity(intent);
        });
    }

    /**
     * inits the vertical layout with buttons for each lexicon section
     * each button Listener loads an html file in the main web view
     */
    private void initLexicon(){
        Button btn = new Button(this);
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