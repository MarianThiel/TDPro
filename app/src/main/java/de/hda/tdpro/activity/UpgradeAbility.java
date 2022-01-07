package de.hda.tdpro.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageButton;

import de.hda.tdpro.R;

public class UpgradeAbility extends AppCompatActivity {

    private ImageButton backButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrade_ability);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initComponents();
        initListeners();
    }

    private void initComponents(){
        backButton = findViewById(R.id.ua_back);
    }

    private void initListeners(){
        backButton.setOnClickListener(e->{
            Intent i = new Intent(UpgradeAbility.this, MainMenuActivity.class);
            startActivity(i);
        });
    }
}