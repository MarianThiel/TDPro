package de.hda.tdpro.core.tower.upgrades;

import de.hda.tdpro.core.Position;
import de.hda.tdpro.core.tower.RangeSphere;
import de.hda.tdpro.core.tower.Tower;


public class TowerDecorator extends Tower {
    Tower embeddedTower;
    public TowerDecorator(Tower uTower) {
        super(0,0,0,0,null);
        embeddedTower = uTower;
    }

    @Override
    public int getDamage() {
        return this.damage + embeddedTower.getDamage();
    }

    @Override
    public int getRadius() {
        return this.radius + embeddedTower.getRadius();
    }

    @Override
    public float getSpeed() {
        return this.speed + embeddedTower.getSpeed();
    }

    @Override
    public int getPrice() {
        return this.price + embeddedTower.getPrice();
    }

    @Override
    public int getLevel() {
        return 1 + embeddedTower.getLevel();
    }

    @Override
    public RangeSphere getSphere() {
        return embeddedTower.getSphere();
    }

    @Override
    public Position getPos() {
        return embeddedTower.getPos();
    }

}
