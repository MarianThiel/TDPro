package de.hda.tdpro.core.tower;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import de.hda.tdpro.core.Drawable;
import de.hda.tdpro.core.EnemyObserver;
import de.hda.tdpro.core.Position;
import de.hda.tdpro.core.enemy.Enemy;

/**
 * @author Marian Thiel
 *  Abstract Class representing a tower
 */
abstract public class Tower implements EnemyObserver, Runnable, Drawable {

    protected int radius;
    protected int damage;
    protected float speed;
    protected int price;
    protected Position pos;

    private boolean running;

    public static final int MAX_LEVEL = 5;

    protected RangeSphere sphere;


    public Tower(int radius, int damage, float speed, int price) {
        this.radius = radius;
        this.damage = damage;
        this.speed = speed;
        this.price = price;
        sphere = null;
        running = true;
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
        if(getSphere().hasEnemyInside()){
            Log.println(Log.ASSERT,"enemy_targeting", this.getClass()+" ENEMY_WAS_HIT - DMG: " + getDamage());
            getSphere().hitEnemy(this.getDamage());
        }

    }

    public int getLevel(){
        return 1;
    }

    public RangeSphere getSphere() {
        return sphere;
    }

    public void terminate(){
        running = false;
    }
    @Override
    public void onEnemyMovement(Enemy e) {
        Position p = e.getPosition();
        if(getSphere().containsEnemy(e)){
            if(!getSphere().intersects(p)){
                getSphere().releaseEnemy(e);
            }
        }else{ // !sphere.containsEnemy(e)
            if(getSphere().intersects(p) && e.getHp()>0){
                getSphere().targetEnemy(e);
                Log.println(Log.ASSERT,"tower","Enemy is in range");
            }
        }
        Log.println(Log.ASSERT,"test", "Number: " + sphere.getNumberOfIntersectedEnemies());

    }

    @Override
    public void run() {
        while(running){
            fireMissile();
            try {
                Thread.sleep ((long) (1000/getSpeed()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
        Paint p = new Paint();
        p.setStyle(Paint.Style.STROKE);
        p.setColor(Color.BLACK);
        p.setStrokeWidth(10);
        canvas.drawCircle(pos.getxVal(),pos.getyVal(),radius,p);
    }
}