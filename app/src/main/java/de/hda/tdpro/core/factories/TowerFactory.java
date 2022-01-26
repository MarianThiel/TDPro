package de.hda.tdpro.core.factories;

import de.hda.tdpro.core.tower.EarthTower;
import de.hda.tdpro.core.tower.FireTower;
import de.hda.tdpro.core.tower.TowerType;
import de.hda.tdpro.core.tower.upgrades.MetaTower;

public class TowerFactory {
    private static TowerFactory instance = null;

    private TowerFactory(){

    }

    public static TowerFactory getInstance(){
        if(instance == null){
            instance = new TowerFactory();
        }
        return instance;
    }

    public FireTower createFireTower(){
        MetaTower meta = MetaTower.getMetaTower(TowerType.FIRE_TOWER);
        return new FireTower(meta.getRange(), meta.getDmg(),meta.getVelocity(),meta.getPrice());
    }

    public EarthTower createEarthTower(){
        MetaTower meta = MetaTower.getMetaTower(TowerType.EARTH_TOWER);
        return new EarthTower(meta.getRange(), meta.getDmg(),meta.getVelocity(),meta.getPrice());
    }
}

