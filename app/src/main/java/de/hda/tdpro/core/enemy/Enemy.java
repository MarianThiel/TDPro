package de.hda.tdpro.core.enemy;

import java.util.LinkedList;

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
public class Enemy implements IntersectionObservable, Runnable {

    private int hp;
    private int armor;
    private float velocity;

    private Position position;

    private final LinkedList<EnemyObserver> observers;

    public Enemy(int hp, int armor, float velocity) {
        this.hp = hp;
        this.armor = armor;
        this.velocity = velocity;
        observers = new LinkedList<>();
    }

    public int getHp() {
        return hp;
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

    }
}
