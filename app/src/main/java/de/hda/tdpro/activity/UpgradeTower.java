package de.hda.tdpro.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Map;

import de.hda.tdpro.ConfigWriter;
import de.hda.tdpro.R;
import de.hda.tdpro.core.GlobalTowerUpgrade;
import de.hda.tdpro.core.tower.Tower;
import de.hda.tdpro.core.tower.TowerType;
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

    private TextView l_price;
    private TextView l_dsc;
    private TextView l_name;

    private MetaTower demoTower;


    private TowerType[] towers;

    private Map<String, GlobalTowerUpgrade> upgrades;

    private int selectedTower;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrade_tower);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        selectedTower = 0;
        towers = TowerType.values();

        initComponents();
        setupListeners();

        selectTower(selectedTower);
        initContextMenu(upgrades.get("stats"));
        demoTower = new MetaTower("Fire Tower",10,80,1,50);

    }

    private void initComponents(){
        back = findViewById(R.id.ua_back2);
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
        l_price = findViewById(R.id.txtprice);
        l_dsc = findViewById(R.id.txtDescr);
        l_name = findViewById(R.id.txtUpgradeType);
    }

    private void setupListeners(){
        back.setOnClickListener(e->{
            Intent i = new Intent(UpgradeTower.this,MainMenuActivity.class);
            startActivity(i);
        });

        left.setOnClickListener(e->{
            selectedTower = Math.floorMod (selectedTower - 1,towers.length);
            selectTower(selectedTower);
            initContextMenu(upgrades.get("stats"));
        });

        right.setOnClickListener(e->{
            selectedTower =  Math.floorMod (selectedTower + 1,towers.length);
            selectTower(selectedTower);
            initContextMenu(upgrades.get("stats"));
        });

        buy.setOnClickListener(e->{
            //buy upgrade write upgrades back to xml
            // write metaTower to xml
        });

        dmg.setOnClickListener(e->{
            //show actual dmg upgrade
            initContextMenu(upgrades.get("stats"));
        });

        vel.setOnClickListener(e->{
            //show actual vel upgrade
            initContextMenu(upgrades.get("level"));
        });

        rng.setOnClickListener(e->{
            //show actual rng upgrade
            initContextMenu(upgrades.get("price"));
        });

    }

    private void initContextMenu(GlobalTowerUpgrade upgrade){
        MetaTower meta = MetaTower.getMetaTower(towers[selectedTower]);
        towerName.setText(meta.getName());
        lvl.setText(Integer.toString(Tower.MAX_LEVEL));
        l_price.setText(Integer.toString(meta.getPrice()) + " + " + upgrade.getValue());
        l_dmg.setText(Integer.toString(meta.getDmg()));
        l_vel.setText(Float.toString(meta.getVelocity()));
        l_rng.setText(Integer.toString(meta.getRange()));
    }

    private void selectTower(int i){
        upgrades = loadUpgrades(towers[i]);
    }

    private Map<String,GlobalTowerUpgrade> loadUpgrades(TowerType type){
        Map<String,GlobalTowerUpgrade> upgrades = ConfigWriter.getInstance().readGlobalTowerUpgrades(type);
        return upgrades;
    }

}