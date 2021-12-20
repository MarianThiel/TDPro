package de.hda.tdpro.core.tower;

import de.hda.tdpro.core.EnemyObserver;
import de.hda.tdpro.core.Position;
import de.hda.tdpro.core.enemy.Enemy;

/**
 * @author Marian Thiel
 *  Abstract Class representing a tower
 */
abstract public class Tower implements EnemyObserver {

    protected int radius;
    protected int damage;
    protected float speed;
    protected int price;
    protected Position pos;


    public static final int MAX_LEVEL = 5;

    protected RangeSphere sphere;


    public Tower(int radius, int damage, float speed, int price) {
        this.radius = radius;
        this.damage = damage;
        this.speed = speed;
        this.price = price;
        sphere = null;
    }

    public Position getPos() {
        return pos;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }


    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void fireMissile(){
        sphere.hitEnemy(damage);
    }

    public int getLevel(){
        return 1;
    }

    public RangeSphere getSphere() {
        return sphere;
    }

    @Override
    public void onEnemyMovement(Enemy e) {
        Position p = e.getPosition();
        if(sphere.intersects(p)){
            //enqueue
            sphere.targetEnemy(e);
        }

    }
}