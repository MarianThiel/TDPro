package de.hda.tdpro.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import de.hda.tdpro.R;
import de.hda.tdpro.core.Game;
import de.hda.tdpro.core.tower.upgrades.UpgradeType;
import de.hda.tdpro.core.tower.upgrades.MetaUpgrade;

public class TowerUpgradeView extends FrameLayout {

    private TextView txtDmg;
    private TextView txtVel;
    private TextView txtRad;
    private TextView txtPrice;
    private TextView txtName;

    private ImageButton buyUpgrade;

    private Game game;

    private UpgradeType type;

    public TowerUpgradeView(@NonNull Context context, Game g) {
        super(context);
        game = g;
        init();
    }

    public TowerUpgradeView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TowerUpgradeView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        inflate(getContext(), R.layout.upgrade_tower_layout,this);


        txtDmg = findViewById(R.id.txtDmg);
        txtVel = findViewById(R.id.txtVel);
        txtRad = findViewById(R.id.txtRad);
        txtPrice = findViewById(R.id.txtPrice);
        //txtName = findViewById(R.id.txtName);
        buyUpgrade = findViewById(R.id.upgradeButton2);

        buyUpgrade.setOnClickListener(e->{
            game.upgradeSelectedTower(type);
        });
    }

    public void updateView(UpgradeType upgrade){
        type = upgrade;
        MetaUpgrade meta = MetaUpgrade.getMetaUpgrade(upgrade);
        //txtName.setText(meta.getNAME());
        txtDmg.setText(Integer.toString(meta.getDMG()));
        txtVel.setText(Float.toString(meta.getVEL()));
        txtRad.setText(Integer.toString(meta.getRAD()));
        txtPrice.setText(Integer.toString(meta.getPRICE()));
    }
}
