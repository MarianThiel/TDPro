package de.hda.tdpro.core.tower;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import de.hda.tdpro.core.Drawable;
import de.hda.tdpro.core.EnemyObserver;
import de.hda.tdpro.core.Intersectable;
import de.hda.tdpro.core.Position;
import de.hda.tdpro.core.ResourceLoader;
import de.hda.tdpro.core.enemy.Enemy;
import de.hda.tdpro.core.tower.priority.Priority;
import de.hda.tdpro.core.tower.projectiles.AbstractProjectile;

/**
 * @author Marian Thiel
 *  Abstract Class representing a tower
 */
abstract public class Tower implements EnemyObserver, Drawable, Intersectable {

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

    protected boolean rotatable;

    /**
     * Image of the Tower as Bitmap
     */
    protected Bitmap img[];

    protected Bitmap current;

    protected AbstractProjectile p;

    protected int hitBox;

    private boolean borders;

    public Tower(int radius, int damage, float speed, int price) {
        this.radius = radius;
        this.damage = damage;
        this.speed = speed;
        this.price = price;

        sphere = null;
        aiming = false;
        active = false;
        hitBox = 50;

        borders = false;
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

    /**
     * sets the hit priority of the tower
     * @param priority the Priority defined by Enum
     */
    public void setPriority(Priority priority){
        getSphere().setPriority(priority);
    }

    public Priority getPriority(){
        return getSphere().getPriority();
    }

    /**
     * starts thread of RangeSphere
     */
    public void startAiming(){
        getSphere().startAiming();

    }

    /**
     * stops thread of RangeSphere
     */
    public void stopAiming(){
        getSphere().stopAiming();
    }


    public int getLevel(){
        return 1;
    }


    public RangeSphere getSphere() {
        return sphere;
    }

    /**
     * returns state of the tower
     * @return true if tower thread was started, false if not
     */
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * intersection method of tower hit box
     * @param p position which has to be checked
     * @return true if p is in hit box, false if not
     */
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

    /**
     * logic for enqueue enemy to the sphere
     * @param e observed enemy
     */
    @Override
    public void onEnemyMovement(Enemy e) {
        if(e != null && !e.isFinished()){
            Position p = e.getPosition();
            if(getSphere().containsEnemy(e)){
                if(!getSphere().intersects(p)){
                    getSphere().releaseEnemy(e);
                }
            }else{ // !sphere.containsEnemy(e)
                if(getSphere().intersects(p) && e.getHp()>0){
                    startAiming();
                    getSphere().targetEnemy(e);
                }
            }
        }

    }

    /**
     * rotates te tower in direction to target position
     * @param p the direction to rotate
     */
    public void rotateTower(Position p){
        current = ResourceLoader.getInstance().getRotated(img[(getLevel()-1) % img.length],this.getPos(),p);
    }

    @Override
    public void draw(Canvas canvas) {
        getSphere().draw(canvas);
        if(getP() != null){
            getP().draw(canvas);
        }

        if(borders){
            Paint p = new Paint();
            p.setColor(Color.parseColor("#7700FF00"));
        }

    }

    /**
     * get the abstract Projectile
     * @return null if no projectile was shot
     */
    public AbstractProjectile getP() {
        return p;
    }

    @Override
    public void onEnemySuccess(Enemy e) {

    }

    @Override
    public void onEnemyDying(Enemy e) {

    }

    /**
     * abstract method to fire a projectile to enemy
     * @param enemies set of enemies which were selected
     * @param damage damage value of tower
     * @param vel velocity of shooting
     */
    public abstract void fire(Enemy[] enemies, int damage, float vel);

    /**
     *
     * @return true if tower was meant to be rotated
     */
    public boolean isRotatable() {
        return rotatable;
    }


    @Override
    public boolean intersects(Position position) {
        return inHitBox(position);
    }

    @Override
    public void showBorders(boolean v) {
        borders = v;
    }
}