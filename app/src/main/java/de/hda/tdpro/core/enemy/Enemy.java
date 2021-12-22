package de.hda.tdpro.core.enemy;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.LinkedList;
import java.util.List;

import de.hda.tdpro.core.Drawable;
import de.hda.tdpro.core.EnemyObserver;
import de.hda.tdpro.core.IntersectionObservable;
import de.hda.tdpro.core.Position;

/**
 * @author Marian Thiel
 * @version 1.1
 *
 * @implNote used as Observable - observed by Towers
 * Thread functionality for walking purposes
 *
 * Class representing an Enemy in the Game
 */
public class Enemy implements IntersectionObservable, Drawable, Runnable {

    private int hp;
    private int armor;
    private float velocity;

    private Thread thread;

    private List<Position> walkingPath;
    private final boolean walking = true;
    private int curpos = 0;

    private Position position;
    private Bitmap image;
    private final LinkedList<EnemyObserver> observers;

    public Enemy(int hp, int armor, float velocity, Bitmap image) {
        this.hp = hp;
        this.armor = armor;
        this.velocity = velocity;
        this.image = image;
        thread = new Thread(this);
        observers = new LinkedList<>();
    }

    public Enemy(int hp, int armor, float velocity) {
        this.hp = hp;
        this.armor = armor;
        this.velocity = velocity;
        observers = new LinkedList<>();
    }

    public void setWalkingPath(Path p){
        walkingPath = p.generateAllPositions();
    }

    public int getHp() {
        return hp;
    }
    public void setImage(Bitmap img){
        this.image = img;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public float getVelocity() {
        return velocity;
    }

    public void setVelocity(float velocity) {
        this.velocity = velocity;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
        notifyObservers();
    }

    private void walkPath(){
        if(curpos<walkingPath.size())
        setPosition(walkingPath.get(curpos++));
    }
    @Override
    public void addEnemyObserver(EnemyObserver o) {
        observers.add(o);
    }

    @Override
    public void removeEnemyObserver(EnemyObserver o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for(EnemyObserver o : observers){
            o.onEnemyMovement(this);
        }
    }

    @Override
    public void run() {
        while(walking){
            walkPath();

            try {
                Thread.sleep((long) (1000/velocity));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
        if(image!=null)
        canvas.drawBitmap(image, position.getxVal(),position.getyVal(),null);
    }

    public void initWalking(){
        thread.start();
    }
}
