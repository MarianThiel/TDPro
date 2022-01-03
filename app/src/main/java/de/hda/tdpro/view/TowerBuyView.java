package de.hda.tdpro.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import de.hda.tdpro.R;
import de.hda.tdpro.core.tower.FireTower;
import de.hda.tdpro.core.tower.Tower;
import de.hda.tdpro.core.tower.TowerManager;
import de.hda.tdpro.core.tower.upgrades.MetaUpgrade;

public class TowerBuyView extends FrameLayout {


    private TextView txtDmg;
    private TextView txtVel;
    private TextView txtRad;
    private TextView txtPrice;
    private TextView txtName;

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
        txtDmg = findViewById(R.id.txtDmg);
        txtVel = findViewById(R.id.txtVel);
        txtRad = findViewById(R.id.txtRad);
        txtPrice = findViewById(R.id.txtPrice);
        txtName = findViewById(R.id.txtName);
        /*
        Button buy = (Button) findViewById(R.);
        buy.setOnClickListener((View.OnClickListener) this);
        */
    }


    public void buyFireView(){
        /*
        txtDmg.setText(Integer.toString( .getDamage()));
        txtVel.setText(Float.toString( .getSpeed()));
        txtRad.setText(Integer.toString(.getRadius()));
        txtPrice.setText(Integer.toString(.getPrice()));
         */
        txtName.setText("FireTower(HARDCODETEST)");
        txtDmg.setText("10");
        txtVel.setText("2");
        txtRad.setText("7");
        txtPrice.setText("100");
    }
}
