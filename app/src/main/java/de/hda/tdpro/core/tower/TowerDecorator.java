package de.hda.tdpro.core.tower;

public class TowerDecorator extends Tower {
    Tower embeddedTower;
    public TowerDecorator(Tower uTower) {
        super(0,0,0,0);
        embeddedTower = uTower;
    }
}
