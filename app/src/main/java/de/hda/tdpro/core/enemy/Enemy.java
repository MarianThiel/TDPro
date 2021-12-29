package de.hda.tdpro.core.enemy;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import de.hda.tdpro.core.Drawable;
import de.hda.tdpro.core.EnemyObserver;
import de.hda.tdpro.core.EnemyObservable;
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
public class Enemy implements EnemyObservable, Runnable, Drawable {

    private int hp;
    private int armor;
    private float velocity;
    private boolean alive;
    private Position position;

    private Thread walkingThread;
    private boolean walking;
    private List<Position> path;
    private Iterator<Position> iterator;

    private Bitmap image;

    private final LinkedList<EnemyObserver> observers;

    /**
     *
     * @param hp
     * @param armor
     * @param velocity
     * @param img
     */
    public Enemy(int hp, int armor, float velocity, Bitmap img) {
        this.hp = hp;
        this.armor = armor;
        this.velocity = velocity;
        alive = true;
        image = img;
        observers = new LinkedList<>();
    }

    /**
     * constructor for TestBench
     * @param hp Hit points of an Enemy
     * @param armor Armor of an Enemy
     * @param velocity velocity of an Enemy
     */
    public Enemy(int hp, int armor, float velocity) {
        this.hp = hp;
        this.armor = armor;
        this.velocity = velocity;
        alive = true;
        observers = new LinkedList<>();
    }

    public int getHp() {
        return hp;
    }

    synchronized public void setHp(int hp) {
        this.hp = hp;
        if(hp <= 0){
            this.hp = 0;
            alive = false;
            stopWalking();
        }
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
        notifyOnMovement();
    }

    public boolean isAlive() {
        return alive;
    }

    public boolean isWalking() {
        return walking;
    }

    public boolean isFinished(){
        return position.equals(path.get(path.size() - 1));
    }

    public void initWalking(){
        walking = true;
        walkingThread = new Thread(this);
        walkingThread.start();
    }
    public void stopWalking(){
        walking = false;
        try {
            walkingThread.join();
        } catch (InterruptedException e) {

        }
    }

    public void setWalkingPath(Path p){
        path = p.generateAllPositions();
        iterator = path.listIterator();
    }

    private void walkStep(){
        if(iterator.hasNext()){
            setPosition(iterator.next());
            Log.println(Log.ASSERT,"walk","walk");
        }else{
            stopWalking();
            Log.println(Log.ASSERT,"walk","stop walking");
        }
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
    public void notifyOnMovement() {
        for(EnemyObserver o : observers){
            o.onEnemyMovement(this);
        }
    }

    @Override
    public void run() {
        while(walking){
            walkStep();
            try {
                Thread.sleep((long) (1000 / velocity));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
        if(position!=null && isAlive()){
            canvas.drawBitmap(image, position.getxVal()-(image.getWidth()/2), position.getyVal()-(image.getHeight()/2),null);
            String s = Integer.toString(hp);
            Paint p = new Paint();
            p.setTextSize(50);
            canvas.drawText(s,position.getxVal()-(image.getWidth()/2),position.getyVal()+10,p);
        }

    }
}
