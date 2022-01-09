package de.hda.tdpro.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import de.hda.tdpro.R;
import de.hda.tdpro.core.tower.upgrades.MetaTower;

public class UpgradeTower extends AppCompatActivity {

    private ImageButton back;

    private ImageButton left;

    private ImageButton right;

    private Button buy;

    private Button dmg;

    private Button vel;

    private Button rng;

    private TextView towerName;

    private TextView upgradeType;

    private TextView lvl;

    private TextView l_dmg;

    private TextView l_vel;

    private TextView l_rng;

    private MetaTower demoTower;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrade_tower);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initComponents();
        setupListeners();
        demoTower = new MetaTower("Fire Tower",10,80,1,50);
        initContextMenu("DMG",2, 0,0);
    }

    private void initComponents(){
        back = findViewById(R.id.ua_back);
        left = findViewById(R.id.btnLeft);
        right = findViewById(R.id.btnRight);
        buy = findViewById(R.id.btnBuyUpgrade);
        dmg = findViewById(R.id.btnUpgradeDMG);
        vel = findViewById(R.id.btnUpgradeVel);
        rng = findViewById(R.id.btnUpgradeRange);

        towerName = findViewById(R.id.txtTowerNameContext);
        upgradeType = findViewById(R.id.txtUpgradeType);
        lvl = findViewById(R.id.txtLevel);
        l_dmg = findViewById(R.id.txtDamage);
        l_vel = findViewById(R.id.txtVelocity);
        l_rng = findViewById(R.id.txtRange);
    }

    private void setupListeners(){
        back.setOnClickListener(e->{
            Intent i = new Intent(UpgradeTower.this,MainMenuActivity.class);
            startActivity(i);
        });

        left.setOnClickListener(e->{
            //go left
        });

        right.setOnClickListener(e->{
            //go right
        });

        buy.setOnClickListener(e->{
            //buy upgrade
        });

        dmg.setOnClickListener(e->{
            //show actual dmg upgrade
            initContextMenu("DMG",2, 0,0);
        });

        vel.setOnClickListener(e->{
            //show actual vel upgrade
            initContextMenu("VEL",0, 0,0.1f);
        });

        rng.setOnClickListener(e->{
            //show actual rng upgrade
            initContextMenu("RNG",0, 5,0);
        });

    }

    private void initContextMenu(String type, int dmg, int rng, float vel){
        lvl.setText("1 -> 2");
        l_dmg.setText(demoTower.getDmg() + " + " + dmg);
        l_rng.setText(demoTower.getRange() + " + " + rng);
        l_vel.setText(demoTower.getVelocity() + " + " + vel);
    }

}