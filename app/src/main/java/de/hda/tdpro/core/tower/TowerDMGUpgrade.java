package de.hda.tdpro.core.tower;

public class TowerDMGUpgrade extends TowerDecorator{
    public TowerDMGUpgrade(Tower uTower) {
        super(uTower);
        this.damage = 10;
    }
}
