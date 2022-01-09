package de.hda.tdpro.core.tower.upgrades;

import android.graphics.Canvas;

import de.hda.tdpro.core.Position;
import de.hda.tdpro.core.tower.RangeSphere;
import de.hda.tdpro.core.tower.Tower;

/**
 * @author Marian Thiel
 * @version 1.2
 *
 * class decorates a tower with upgrade
 * the attribute values are accumulated
 */
public abstract class TowerDecorator extends Tower {
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

    /**
     * get the Sphere
     * @return returns the sphere of base tower
     */
    @Override
    public RangeSphere getSphere() {
        return embeddedTower.getSphere();
    }

    /**
     * get the current Position
     * @return Position of the base Tower
     */
    @Override
    public Position getPos() {
        return embeddedTower.getPos();
    }

    @Override
    public boolean isActive() {
        return embeddedTower.isActive();
    }

    @Override
    public void setActive(boolean active) {
        embeddedTower.setActive(active);
    }

    @Override
    public void setPos(Position pos) {
        embeddedTower.setPos(pos);
    }



    @Override
    public void draw(Canvas canvas) {
        //super.draw(canvas);
        embeddedTower.draw(canvas);
    }
}
