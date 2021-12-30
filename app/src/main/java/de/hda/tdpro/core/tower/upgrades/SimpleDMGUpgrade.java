package de.hda.tdpro.core.tower.upgrades;

import de.hda.tdpro.core.tower.Tower;

/**
 * has to be changed to a more general Upgrade, should use  MetaUpgrade to initial values
 */
public class SimpleDMGUpgrade extends TowerDecorator {
    public SimpleDMGUpgrade(Tower uTower) {
        super(uTower);
        this.damage = 20;
    }
}
