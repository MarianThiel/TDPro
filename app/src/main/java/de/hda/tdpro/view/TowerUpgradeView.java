package de.hda.tdpro.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;

import de.hda.tdpro.R;
import de.hda.tdpro.core.tower.upgrades.MetaUpgrade;

public class TowerUpgradeView extends FrameLayout {

    private TextView txtDmg;
    private TextView txtVel;
    private TextView txtRad;
    private TextView txtPrice;
    private TextView txtName;

    public TowerUpgradeView(@NonNull Context context) {
        super(context);
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
        txtName = findViewById(R.id.txtName);
    }

    public void updateView(MetaUpgrade upgrade){
        txtName.setText(upgrade.getNAME());
        txtDmg.setText(Integer.toString(upgrade.getDMG()));
        txtVel.setText(Float.toString(upgrade.getVEL()));
        txtRad.setText(Integer.toString(upgrade.getRAD()));
        txtPrice.setText(Integer.toString(upgrade.getPRICE()));
    }
}
