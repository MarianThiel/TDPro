package de.hda.tdpro.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import de.hda.tdpro.R;
import de.hda.tdpro.core.tower.FireTower;
import de.hda.tdpro.core.tower.Tower;
import de.hda.tdpro.core.tower.TowerManager;
import de.hda.tdpro.core.tower.TowerType;
import de.hda.tdpro.core.tower.upgrades.MetaTower;
import de.hda.tdpro.core.tower.upgrades.MetaUpgrade;

public class TowerBuyView extends FrameLayout {

    private LinearLayout towers;

    public TowerBuyView(@NonNull Context context) {
        super(context);
        init();
    }

    public TowerBuyView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TowerBuyView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        inflate(getContext(), R.layout.tower_buy_layout,this);
        towers = findViewById(R.id.towerList);
        /*
        Button buy = (Button) findViewById(R.);
        buy.setOnClickListener((View.OnClickListener) this);
        */
    }

    public void addTowerView(TowerView towerView){
        towers.addView(towerView);
    }


}
