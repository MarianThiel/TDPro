package de.hda.tdpro.core.tower;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import de.hda.tdpro.core.Drawable;
import de.hda.tdpro.core.EnemyObserver;
import de.hda.tdpro.core.Position;
import de.hda.tdpro.core.enemy.Enemy;
import de.hda.tdpro.core.tower.priority.Priority;

/**
 * @author Marian Thiel
 *  Abstract Class representing a tower
 */
abstract public class Tower implements EnemyObserver, Runnable, Drawable {

    /**
     * radius as integer value in pixel
     */
    protected int radius;
    /**
     * damage of the tower
     */
    protected int damage;
    /**
     * value indicates the attack speed 1 means 1 hit per second 2 = 2 hits per second
     */
    protected float speed;
    /**
     * price of placing tower
     */
    protected int price;
    /**
     * position of the tower on the map
     */
    protected Position pos;
    /**
     * thread holds instance of the tower, is used for attacking enemies in sphere
     */
    private Thread aimThread;
    /**
     * true if tower is aiming an enemy - means thread is running
     */
    private boolean aiming;
    /**
     * Max level of tower
     */
    public static final int MAX_LEVEL = 5;
    /**
     * the sphere of the tower
     */
    protected RangeSphere sphere;

    private Context context;

    protected Bitmap img;

    public Tower(int radius, int damage, float speed, int price, Context context) {
        this.radius = radius;
        this.damage = damage;
        this.speed = speed;
        this.price = price;
        this.context = context;
        sphere = null;
        aiming = false;
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
            Log.println(Log.ASSERT,"enemy_targeting", this.getClass() + " ENEMY_WAS_HIT - DMG: " + getDamage());
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

    public void setHitPriority(Priority type){

        sphere.setPriority(type);

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
        while(aiming){
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