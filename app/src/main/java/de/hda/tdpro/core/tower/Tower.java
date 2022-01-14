package de.hda.tdpro.core.tower;

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
abstract public class Tower implements EnemyObserver, Drawable {

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
     * true if tower is aiming an enemy - means thread is running
     */
    private boolean aiming;
    /**
     * Max level of tower
     */
    public static final int MAX_LEVEL = 50;
    /**
     * the sphere of the tower
     */
    protected RangeSphere sphere;
    /**
     * active is the selection state
     */
    protected boolean active;

    /**
     * Image of the Tower as Bitmap
     */
    protected Bitmap img[];


    protected int hitBox;

    public Tower(int radius, int damage, float speed, int price) {
        this.radius = radius;
        this.damage = damage;
        this.speed = speed;
        this.price = price;

        sphere = null;
        aiming = false;
        active = false;
        hitBox = 50;
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





    public void startAiming(){
        getSphere().startAiming();

    }
    public void stopAiming(){
        getSphere().stopAiming();
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    protected boolean inHitBox(Position p){
        Position pc = getPos();
        int px = p.getxVal()-pc.getxVal();
        int py = p.getyVal()-pc.getyVal();
        double distance = Math.sqrt((px*px)+(py*py));

        return distance <= hitBox;
    }

    public Bitmap[] getImg() {
        return img;
    }

    @Override
    public void onEnemyMovement(Enemy e) {
        if(e != null){
            Position p = e.getPosition();
            if(getSphere().containsEnemy(e)){
                if(!getSphere().intersects(p)){
                    getSphere().releaseEnemy(e);
                }
            }else{ // !sphere.containsEnemy(e)
                if(getSphere().intersects(p) && e.getHp()>0){
                    getSphere().targetEnemy(e);
                }
            }
        }

    }


    @Override
    public void draw(Canvas canvas) {
        getSphere().draw(canvas);

        //drawSphere(canvas);
    }

    protected void drawSphere(Canvas canvas) {
        if(isActive()){
            Paint p = new Paint();
            p.setStyle(Paint.Style.FILL_AND_STROKE);
            p.setColor(Color.parseColor("#8fe9ff"));
            p.setStrokeWidth(10);
            p.setAlpha(80);
            canvas.drawCircle(getPos().getxVal(),getPos().getyVal(),getRadius(),p);
        }
    }

    @Override
    public void onEnemySuccess(Enemy e) {

    }

    @Override
    public void onEnemyDying(Enemy e) {

    }
}