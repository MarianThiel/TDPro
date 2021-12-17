package de.hda.tdpro.core.tower;

public class TowerDecorator extends Tower {
    Tower embeddedTower;
    public TowerDecorator(Tower uTower) {
        super(0,0,0,0);
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
}
