package de.hda.tdpro.core.tower;

public class TowerDecorator extends Tower {
    Tower embeddedTower;
    public TowerDecorator(Tower uTower) {
        super(0,0,0,0);
        embeddedTower = uTower;
    }

    @Override
    public int getDamage() {
        return this.damage + super.getDamage();
    }

    @Override
    public int getRadius() {
        return this.radius + super.getRadius();
    }

    @Override
    public float getSpeed() {
        return this.speed + super.getSpeed();
    }

    @Override
    public int getPrice() {
        return this.price + super.getPrice();
    }
}
