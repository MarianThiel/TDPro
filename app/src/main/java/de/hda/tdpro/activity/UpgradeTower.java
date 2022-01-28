package de.hda.tdpro.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

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
    private TextView amountOfDiamonds;


    private TowerType[] towers;

    private Map<String, GlobalTowerUpgrade> upgrades;

    private int selectedTower;

    private String selectedUpgradeKey;

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
        initStatsUpgrade();



    }

    private void initComponents(){
        back = findViewById(R.id.ua_back2);
        left = findViewById(R.id.btnLeft);
        right = findViewById(R.id.btnRight);
        buy = findViewById(R.id.btnBuyUpgrade);
        dmg = findViewById(R.id.btnUpgradeDMG);
        vel = findViewById(R.id.btnUpgradeVel);
        rng = findViewById(R.id.btnUpgradeRange);
        amountOfDiamonds = findViewById(R.id.amountOfDiamonds);
        dmg.setActivated(true);


        towerName = findViewById(R.id.txtTowerNameContext);
        upgradeType = findViewById(R.id.txtUpgradeType);
        lvl = findViewById(R.id.txtLevel);
        l_dmg = findViewById(R.id.txtDamage);
        l_vel = findViewById(R.id.txtVelocity);
        l_rng = findViewById(R.id.txtRange);
        l_price = findViewById(R.id.txtprice);
        l_dsc = findViewById(R.id.txtDescr);

    }

    private void setupListeners(){
        back.setOnClickListener(e->{
            Intent i = new Intent(UpgradeTower.this,MainMenuActivity.class);
            startActivity(i);
        });

        left.setOnClickListener(e->{
            selectedTower = Math.floorMod (selectedTower - 1,towers.length);
            selectTower(selectedTower);
            initContextMenu();
        });

        right.setOnClickListener(e->{
            selectedTower =  Math.floorMod (selectedTower + 1,towers.length);
            selectTower(selectedTower);
            initContextMenu();
        });

        buy.setOnClickListener(e->{
            //buy upgrade write upgrades back to xml
            // write metaTower to xml

            // 1. determine upgradeType
            MetaTower meta = MetaTower.getMetaTower(towers[selectedTower]);
            GlobalTowerUpgrade u = upgrades.get(selectedUpgradeKey);
            // 2. check price, price <= diamonds
            int price = (int)(u.getBase());
            int money = ConfigWriter.getInstance().readDiamonds();
            if(price <= money){
                // |(true)
                // |-> create MetaTower, edit values
                ConfigWriter.getInstance().writeDiamonds(ConfigWriter.getInstance().readDiamonds() - price);
                MetaTower m = getUpgradedTower(selectedUpgradeKey,meta,u);
                // |-> write MetaTower
                ConfigWriter.getInstance().writeTowerStats(m,towers[selectedTower]);
                // |-> modify upgrade, write upgrade
                u.setCurrentLevel(u.getCurrentLevel()+1);
                u.setBase(u.getBase()*u.getMulti());
                ConfigWriter.getInstance().writeTowerUpgrade(selectedUpgradeKey,towers[selectedTower],u);
                initContextMenu();
            }else{
                Toast t = Toast.makeText(this,"Can't afford Upgrade", Toast.LENGTH_SHORT);
                t.show();
                // |(false)
                // |-> abort -> print os Toast
            }

        });

        dmg.setOnClickListener(e->{
            //show actual dmg upgrade
            initStatsUpgrade();
            toggleButton(dmg);
        });

        vel.setOnClickListener(e->{
            //show actual vel upgrade
            initLevelUpgrade();
            toggleButton(vel);
        });

        rng.setOnClickListener(e->{
            //show actual rng upgrade
            initPriceUpgrade();
            toggleButton(rng);
        });

    }

    private void initContextMenu(){
        MetaTower meta = MetaTower.getMetaTower(towers[selectedTower]);
        towerName.setText(meta.getName());
        lvl.setText(Integer.toString(meta.getMaxLevel()));
        l_price.setText(Integer.toString((int) meta.getPrice()));
        l_dmg.setText(Integer.toString((int) meta.getDmg()));
        l_vel.setText(Float.toString(meta.getVelocity()).subSequence(0,3));
        l_rng.setText(Integer.toString((int)meta.getRange()));
        amountOfDiamonds.setText(ConfigWriter.getInstance().readDiamonds() + "");


    }

    private void initStatsUpgrade(){
        initContextMenu();
        GlobalTowerUpgrade upgrade = upgrades.get("stats");
        selectedUpgradeKey = "stats";
        upgradeType.setText("STATS");
        l_dmg.setText(l_dmg.getText() +" + "+ (int)upgrade.getValue());
        l_rng.setText(l_rng.getText() + " + " + (int)upgrade.getValue());
        l_vel.setText(l_vel.getText().subSequence(0,3) + " + " + (1 / (4 * upgrade.getValue())));

        l_dsc.setText(upgrade.getDescription());
        buy.setText("buy Upgrade (" + upgrade.getBase() + ")");

    }

    private void initLevelUpgrade(){
        initContextMenu();
        GlobalTowerUpgrade upgrade = upgrades.get("level");
        selectedUpgradeKey = "level";
        upgradeType.setText("MAX LEVEL");
        lvl.setText(lvl.getText() + " + " + (int)upgrade.getValue());
        l_dsc.setText(upgrade.getDescription());
        buy.setText("buy Upgrade (" + (int)upgrade.getBase() + ")");
    }

    private void initPriceUpgrade(){
        initContextMenu();
        GlobalTowerUpgrade upgrade = upgrades.get("price");
        selectedUpgradeKey = "price";
        upgradeType.setText("PRICE");
        l_price.setText(l_price.getText() + " - " + (int)upgrade.getValue());
        l_dsc.setText(upgrade.getDescription());
        buy.setText("buy Upgrade (" + (int)upgrade.getBase() + ")");
    }
    private void selectTower(int i){
        upgrades = loadUpgrades(towers[i]);
    }

    private Map<String,GlobalTowerUpgrade> loadUpgrades(TowerType type){
        Map<String,GlobalTowerUpgrade> upgrades = ConfigWriter.getInstance().readGlobalTowerUpgrades(type);
        return upgrades;
    }



    private void toggleButton(Button b){
        dmg.setActivated(false);
        vel.setActivated(false);
        rng.setActivated(false);
        b.setActivated(true);
    }

    private MetaTower getUpgradedTower(String key, MetaTower meta, GlobalTowerUpgrade u){
        switch(key){
            case "stats":
                return new MetaTower(meta.getName(), (meta.getDmg() + (int) u.getValue()), (meta.getRange() + (int)u.getValue()),meta.getVelocity() + (1/(4* u.getValue())),(meta.getPrice()), meta.getMaxLevel());
            case "price":
                return new MetaTower(meta.getName(), meta.getDmg(), meta.getRange(),meta.getVelocity(),(meta.getPrice() - (int) u.getValue()), meta.getMaxLevel());
            case "level":
                return new MetaTower(meta.getName(), meta.getDmg(), meta.getRange(),meta.getVelocity(),meta.getPrice(), meta.getMaxLevel() +1);

        }
        return null;
    }
}