package de.hda.tdpro.core.tower;

import android.util.Log;

import de.hda.tdpro.core.EnemyObserver;
import de.hda.tdpro.core.Position;
import de.hda.tdpro.core.enemy.Enemy;

/**
 * @author Marian Thiel
 *  Abstract Class representing a tower
 */
abstract public class Tower implements EnemyObserver, Runnable {

    protected int radius;
    protected int damage;
    protected float speed;
    protected int price;
    protected Position pos;

    private Thread aimThread;

    private boolean aiming;

    public static final int MAX_LEVEL = 5;

    protected RangeSphere sphere;


    public Tower(int radius, int damage, float speed, int price) {
        this.radius = radius;
        this.damage = damage;
        this.speed = speed;
        this.price = price;
        sphere = null;
        aiming = true;
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

    public boolean isAiming() {
        return aiming;
    }

    public void fireMissile(){
        if(getSphere().hasEnemyInside()){
            Log.println(Log.ASSERT,"enemy_targeting", this.getClass()+" ENEMY_WAS_HIT - DMG: " + getDamage());
            getSphere().hitEnemy(this.getDamage());
        }

    }

    public void startAiming(){
        aiming = true;
        aimThread = new Thread(this);
        aimThread.start();
    }
    public void stopAiming(){
        aiming = false;
        try {
            aimThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
        if(getSphere().containsEnemy(e)){
            if(!getSphere().intersects(p)){
                getSphere().releaseEnemy(e);
            }
        }else{ // !sphere.containsEnemy(e)
            if(getSphere().intersects(p)){
                getSphere().targetEnemy(e);
            }
        }

    }

    @Override
    public void run() {
        while(aiming){
            fireMissile();
            try {
                Thread.sleep ((long) (1000/getSpeed()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}