package de.hda.tdpro.core.tower.upgrades;

import de.hda.tdpro.core.tower.Tower;

public class SimpleDMGUpgrade extends TowerDecorator {
    public SimpleDMGUpgrade(Tower uTower) {
        super(uTower);
        this.damage = 20;
    }
}
