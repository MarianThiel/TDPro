package de.hda.tdpro.core.tower;

/**
 * @author Marian Thiel
 *  Abstract Class representing a tower
 */
abstract public class Tower {

    protected int radius;
    protected int damage;
    protected float speed;
    protected int price;



    private static final int MAX_LEVEL = 5;

    RangeSphere sphere;


    public Tower(int radius, int damage, float speed, int price) {
        this.radius = radius;
        this.damage = damage;
        this.speed = speed;
        this.price = price;
        sphere = new RangeSphere(radius);
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


}