package de.hda.tdpro.core.tower;

import java.util.Arrays;

import de.hda.tdpro.core.factories.TowerFactory;

public class TowerManager {

    private final int MAX_TOWER_NUMBER;

    private final Tower[] towers;

    public TowerManager(int MAX_TOWER_NUMBER) {
        this.MAX_TOWER_NUMBER = MAX_TOWER_NUMBER;
        towers = new Tower[MAX_TOWER_NUMBER];
        Arrays.fill(towers, null);
    }

    public boolean upgradeTower(int i,  UpgradeType upg){
        if(i >= 0 && i < towers.length){
            if(towers[i] != null){
                if(upgradePossible(i)){
                    switch (upg){
                        case DMG_UPGRADE:
                            towers[i].terminate();
                            towers[i] = new TowerDMGUpgrade(towers[i]);
                            Thread th = new Thread(towers[i]);
                            th.start();
                            break;
                        case RANGE_UPGRADE:
                            break;
                    }
                    return true;
                }
            }
        }
            return false;
    }

    public boolean placeTower(int i, TowerType type){
        if(i >= 0 && i < towers.length){
            if(towers[i] == null){
                switch (type){
                    case FIRE_TOWER:
                        towers[i] = TowerFactory.getInstance().createFireTower();
                        Thread t = new Thread(towers[i]);
                        t.start();
                        break;
                    case ICE_TOWER:

                        break;
                }
                return true;
            }
        }
        return false;
    }

    public Tower getTower(int i){
        if(i >= 0 && i < towers.length){
            return towers[i];
        }
        return null;
    }

    private boolean upgradePossible(int i){
        return towers[i].getLevel()<Tower.MAX_LEVEL;
    }
}
