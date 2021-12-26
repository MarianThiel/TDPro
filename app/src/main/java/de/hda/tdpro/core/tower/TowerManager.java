package de.hda.tdpro.core.tower;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import de.hda.tdpro.core.Position;
import de.hda.tdpro.core.factories.TowerFactory;

public class TowerManager {

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
    public boolean upgradeTower(int i,  UpgradeType upg){
        if(i >= 0 && i < towers.length){
            if(towers[i] != null){
                if(upgradePossible(i)){
                    towers[i].stopAiming();
                    switch (upg){
                        case DMG_UPGRADE:
                            towers[i] = new TowerDMGUpgrade(towers[i]);
                            break;
                        case RANGE_UPGRADE:
                            break;
                    }
                    towers[i].startAiming();
                    return true;
                }
            }
        }
            return false;
    }

    public boolean placeTower(TowerType type, Position position){
                if(idx<MAX_TOWER_NUMBER){
                    switch (type){
                        case FIRE_TOWER:
                            towers[idx] = TowerFactory.getInstance().createFireTower();
                            break;
                        case ICE_TOWER:

                            break;
                    }
                    towers[idx].setPos(position);
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
}
