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
    /**
     *  Hit points of enemy
     */
    private int hp;
    /**
     * armor of enemy | note useless until now
     */
    private int armor;
    /**
     * velocity of enemy in steps/seconds
     */
    private float velocity;
    /**
     * alive state of enemy
     */
    private boolean alive;
    /**
     * the current Position of an Enemy
     */
    private Position position;
    /**
     * Thread for Walking behavior
     */
    private Thread walkingThread;
    /**
     * termination variable of thread
     */
    private boolean walking;
    /**
     * absolute path to walk
     */
    private List<Position> path;
    /**
     * iterator of positions
     */
    private Iterator<Position> iterator;
    /**
     * Image of enemy
     */
    private Bitmap[] image;
    /**
     * observers of enemy
     */
    private final LinkedList<EnemyObserver> observers;

    private boolean fin;

    private final long SLEEP;

    private int imageIndex;

    private int stepCount;

    private int positionAsInt;

    /**
     * default constructor
     * @param hp
     * @param armor
     * @param velocity
     * @param img
     */
    public Enemy(int hp, int armor, float velocity, Bitmap[] img) {
        this.hp = hp;
        this.armor = armor;
        this.velocity = velocity;
        alive = true;
        image = img;
        observers = new LinkedList<>();
        fin = false;
        SLEEP = (long)(1000/velocity);
        imageIndex = 0;
        stepCount = 0;
        positionAsInt = 0;
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

        SLEEP = (long)(1000/velocity);
    }

    public int getHp() {
        return hp;
    }

    /**
     * method sets the hp of an Enemy
     * synchronized because multiple tower threads access method
     * stops walking if hp <= 0
     * @param hp hp to be set
     */
    synchronized public void setHp(int hp) {
        this.hp = hp;
        if(hp <= 0){
            this.hp = 0;
            alive = false;
            stopWalking();
            notifyEnemyDying();
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

    /**
     * sets current Positon of enemy notifies observers on movement
     * @param position
     */
    public void setPosition(Position position) {
        this.position = position;
        positionAsInt++;
        notifyOnMovement();
    }

    public boolean isAlive() {
        return alive;
    }

    public boolean isWalking() {
        return walking;
    }

    public boolean isFinished(){
        return fin;
    }

    /**
     * starts the thread
     */
    public void initWalking(){
        if(walkingThread == null || walkingThread.isInterrupted() || !walkingThread.isAlive()){
            walking = true;
            walkingThread = new Thread(this);
            walkingThread.start();
        }
    }

    /**
     * stops the thread
     */
    public void stopWalking(){
        walking = false;
        walkingThread.interrupt();
        try {
            walkingThread.join();
        } catch (InterruptedException e) {

        }
    }

    /**
     * sets the path as List of Positions
     * inits the Iterator
     * @param path
     */
    public void setWalkingPath(List<Position> path){
        this.path = path;
        iterator = this.path.listIterator();
    }

    /**
     * Current position is next position
     * when end of list is reached walking stops
     * Note: the else condition means Enemy has reached end of path
     *       important for loosing condition
     */
    private void walkStep(){
        if(iterator.hasNext()){
            setPosition(iterator.next());
            //Log.println(Log.ASSERT,"walk","walk");
        }else{
            Log.println(Log.ASSERT,"walk","stop walking");
            fin = true;
            notifyEnemySuccess();
            stopWalking();


        }
    }

    public int getPositionAsIndex(){
        return positionAsInt;
    }

    public boolean isOnScreen(){
        return position != null && !isFinished();
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
    public void notifyEnemyDying() {
        synchronized (observers) {
            for (EnemyObserver o : observers) {
                o.onEnemyDying(this);
            }
        }
    }

    @Override
    public void notifyEnemySuccess() {
        synchronized (observers){
            for(EnemyObserver o : observers){
                o.onEnemySuccess(this);
            }
        }

    }

    @Override
    public void run() {
        while(walking){
            if (stepCount % 6 == 0){
                imageIndex = (imageIndex + 1) % image.length;
            }
            walkStep();
            stepCount++;

            try {
                Thread.sleep(SLEEP);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
        if(position != null){
            canvas.drawBitmap(image[imageIndex], position.getxVal()-(image[imageIndex].getWidth()/2), position.getyVal()-(image[imageIndex].getHeight()/2),null);
            String s = Integer.toString(hp);
            Paint p = new Paint();
            p.setTextSize(50);
            canvas.drawText(s,position.getxVal()-(image[imageIndex].getWidth()/2),position.getyVal()+10,p);
        }

    }
}
