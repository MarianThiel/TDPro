package de.hda.tdpro.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import de.hda.tdpro.R;
import de.hda.tdpro.activity.InGameActivity;
import de.hda.tdpro.core.Game;
import de.hda.tdpro.core.tower.TowerType;
import de.hda.tdpro.core.tower.upgrades.MetaTower;
import de.hda.tdpro.core.util.ResourceLoader;

public class TowerView extends FrameLayout {

    private TextView costs;
    private TextView dmg;
    private TextView vel;
    private TextView rng;

    private ImageButton buy;

    private Game game;

    private TowerType type;


    private ImageView img;
    private Context context;

    public TowerView(@NonNull Context context, TowerType type) {
        super(context);

        this.type= type;
        this.context = context;
        initComponents();
        upgradeView(type);

    }

    public TowerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TowerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initComponents(){
        inflate(getContext(), R.layout.tower_view,this);
        costs = findViewById(R.id.txtcosts);
        dmg = findViewById(R.id.txtdmg);
        vel = findViewById(R.id.txtvel);
        rng = findViewById(R.id.txtrng);
        img = findViewById(R.id.image);
        buy = findViewById(R.id.buyTower);

        img.setImageBitmap(ResourceLoader.getInstance().getTowerImages(type)[0]);

        buy.setOnClickListener(e->{
            ((InGameActivity)context).setTowerPlacingMode(type);
            ((InGameActivity)context).hideContextMenu();
        });
    }

    private void upgradeView(TowerType meta) {
        MetaTower m = MetaTower.getMetaTower(meta);

        dmg.setText(Integer.toString(m.getDmg()));
        vel.setText(Float.toString(m.getVelocity()));
        rng.setText(Integer.toString(m.getRange()));
        costs.setText(Integer.toString(m.getPrice()));

    }
}
