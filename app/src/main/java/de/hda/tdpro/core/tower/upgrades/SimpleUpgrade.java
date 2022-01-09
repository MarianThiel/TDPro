package de.hda.tdpro.core.tower.upgrades;

import de.hda.tdpro.core.tower.Tower;

/**
 * has to be changed to a more general Upgrade, should use  MetaUpgrade to initial values
 */
public class SimpleUpgrade extends TowerDecorator {
    public SimpleUpgrade(Tower uTower, MetaUpgrade meta) {
        super(uTower);
        this.damage = meta.getDMG();
        this.speed = meta.getVEL();
        this.radius = meta.getRAD();
    }
}
