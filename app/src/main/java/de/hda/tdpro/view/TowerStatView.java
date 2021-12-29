package de.hda.tdpro.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import de.hda.tdpro.R;
import de.hda.tdpro.core.tower.Tower;

public class TowerStatView extends FrameLayout {

    private TextView txt_dmg;

    private TextView txt_vel;

    private TextView txt_rad;

    private TextView txt_lvl;

    private LinearLayout upgradeContainer;

    public TowerStatView(@NonNull Context context) {
        super(context);
        init();
    }

    public TowerStatView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TowerStatView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        inflate(getContext(), R.layout.tower_selection_layout,this);

        txt_dmg = findViewById(R.id.txt_dmg);
        txt_vel = findViewById(R.id.txt_vel);
        txt_rad = findViewById(R.id.txt_rad);
        txt_lvl = findViewById(R.id.txt_lvl);
        upgradeContainer = findViewById(R.id.upgrade_container);
    }
    public void initTower(Tower t){
        txt_dmg.setText(Integer.toString(t.getDamage()));
        txt_lvl.setText(Integer.toString(t.getLevel()));
        txt_rad.setText(Integer.toString(t.getRadius()));
        txt_vel.setText(Float.toString(t.getSpeed()));
    }
    public void AddUpgradeView(TowerUpgradeView view){
        upgradeContainer.addView(view);
    }
}
