package de.hda.tdpro.core.tower;

import android.content.Context;
import android.graphics.Canvas;

import java.util.Arrays;


import de.hda.tdpro.core.Drawable;
import de.hda.tdpro.core.Position;
import de.hda.tdpro.core.enemy.Enemy;
import de.hda.tdpro.core.factories.TowerFactory;
import de.hda.tdpro.core.tower.upgrades.MetaUpgrade;
import de.hda.tdpro.core.tower.upgrades.SimpleDMGUpgrade;

public class TowerManager implements Drawable {

    private final int MAX_TOWER_NUMBER;

    private final Tower[] towers;

    private int idx;

    private final Context context;


    public TowerManager(int MAX_TOWER_NUMBER, Context context) {
        this.MAX_TOWER_NUMBER = MAX_TOWER_NUMBER;
        towers = new Tower[MAX_TOWER_NUMBER];
        idx = 0;
        Arrays.fill(towers, null);
        this.context = context;
    }

    private int getIndex(Tower tower){
        for(int i = 0; i < MAX_TOWER_NUMBER; i++){
            if(towers[i].equals(tower)){
                return i;
            }
        }
        return -1;
    }
    public boolean upgradeTower(Tower t, MetaUpgrade upgrade){

        int i = getIndex(t);
        if(upgradePossible(i)){
            towers[i].stopAiming();
            towers[i] = new SimpleDMGUpgrade(towers[i]);
            towers[i].startAiming();
            return true;
        }

        return false;
    }

    public boolean placeTower(TowerType type, Position position){
                if(idx<MAX_TOWER_NUMBER){
                    switch (type){
                        case FIRE_TOWER:
                            towers[idx] = TowerFactory.getInstance().createFireTower(context);
                            break;
                        case ICE_TOWER:

                            break;
                    }
                    towers[idx].setPos(position);
                    towers[idx].startAiming();
                    ++idx;
                    return true;
                }else
                return false;
    }

    public Tower getTower(int i){
        if(i >= 0 && i < towers.length){
            return towers[i];
        }
        return null;
    }
    public Tower getTowerByPosition(int x, int y){
        for (Tower  t : towers) {
            if (t != null) {
                if (t.sphere.intersects(new Position(x, y))) {
                    return t;
                }
            }
        }
        return null;
    }

    private boolean upgradePossible(int i){
        return towers[i].getLevel()<Tower.MAX_LEVEL;
    }

    public void addTowerAsListener(Enemy e){
        for(int i = 0; i < MAX_TOWER_NUMBER; i++){
            e.addEnemyObserver(towers[i]);
        }
    }

    public void deselectAllTowers(){
        for (Tower t : towers){
            if(t!=null)
                t.setActive(false);
        }
    }


    @Override
    public void draw(Canvas canvas) {
        for (int i = 0; i < MAX_TOWER_NUMBER; i++){
            if(towers[i] != null){
                towers[i].draw(canvas);
            }
        }
    }
}
