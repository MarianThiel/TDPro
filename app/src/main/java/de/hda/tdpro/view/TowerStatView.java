package de.hda.tdpro.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.hda.tdpro.R;
import de.hda.tdpro.core.Game;
import de.hda.tdpro.core.tower.Tower;
import de.hda.tdpro.core.tower.priority.Priority;

public class TowerStatView extends FrameLayout {

    private TextView txt_dmg;

    private TextView txt_vel;

    private TextView txt_rad;

    private TextView txt_lvl;

    private LinearLayout upgradeContainer;

    private Spinner prioritySpinner;



    private Game game;



    public TowerStatView(@NonNull Context context, Game game) {
        super(context);
        this.game = game;
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

        prioritySpinner = findViewById(R.id.spinner_priority);


        initSpinner();
    }
    public void initTower(Tower t){
        txt_dmg.setText(Integer.toString(t.getDamage()));
        txt_lvl.setText(Integer.toString(t.getLevel()) + "/" + t.getMaxLevel());
        txt_rad.setText(Integer.toString(t.getRadius()));
        txt_vel.setText(Float.toString(t.getSpeed()).substring(0,3));
    }
    public void AddUpgradeView(TowerUpgradeView view){
        upgradeContainer.addView(view);
    }

    private void initSpinner(){
        ArrayList<Priority> l = new ArrayList<>(Arrays.asList(Priority.values()));
        List<String> priorities = new ArrayList<>();
        for(Priority p : l){
            priorities.add(p.toString());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext(),
                android.R.layout.simple_spinner_item,
                priorities);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        prioritySpinner.setAdapter(adapter);
        Priority priority = game.getSelectedTower().getPriority();
        for(String s : priorities){
            if(priority.toString().equals(s)){
                prioritySpinner.setSelection(priorities.indexOf(s));
                break;
            }
        }

        prioritySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(game.getSelectedTower() != null){
                    String x = (String) parent.getItemAtPosition(position);
                    game.getSelectedTower().setPriority(Priority.valueOf(x));
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
