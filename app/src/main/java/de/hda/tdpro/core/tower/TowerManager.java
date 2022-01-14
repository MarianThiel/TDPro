package de.hda.tdpro.core.tower;

import android.graphics.Canvas;

import java.util.Arrays;


import de.hda.tdpro.core.Drawable;
import de.hda.tdpro.core.Position;
import de.hda.tdpro.core.enemy.Enemy;
import de.hda.tdpro.core.factories.TowerFactory;
import de.hda.tdpro.core.tower.upgrades.MetaUpgrade;
import de.hda.tdpro.core.tower.upgrades.SimpleUpgrade;

public class TowerManager implements Drawable {

    private final int MAX_TOWER_NUMBER;

    private final Tower[] towers;

    private int idx;




    public TowerManager(int MAX_TOWER_NUMBER) {
        this.MAX_TOWER_NUMBER = MAX_TOWER_NUMBER;
        towers = new Tower[MAX_TOWER_NUMBER];
        idx = 0;
        Arrays.fill(towers, null);

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

            towers[i].setActive(false);
            towers[i] = new SimpleUpgrade(towers[i],upgrade);

            return true;
        }

        return false;
    }

    public Tower placeTower(TowerType type, Position position){
                if(idx<MAX_TOWER_NUMBER){
                    switch (type){
                        case FIRE_TOWER:
                            towers[idx] = TowerFactory.getInstance().createFireTower();
                            break;
                        case ICE_TOWER:

                            break;
                    }
                    towers[idx].setPos(position);
                    towers[idx].startAiming();
                    Tower t = towers[idx];
                    ++idx;
                    return t;
                }else
                return null;
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
                if (t.inHitBox(new Position(x, y))) {
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
            if(towers[i]!=null)
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

    public void pauseTowers(){
        for(Tower t : towers){
            if(t!=null){
                t.stopAiming();
            }
        }
    }

    public void resumeTowers(){
        for(Tower t: towers){
            if (t != null){
                t.startAiming();
            }
        }
    }
}
