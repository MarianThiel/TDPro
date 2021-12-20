package de.hda.tdpro.core.enemy;

import de.hda.tdpro.core.IntersectionObservable;
import de.hda.tdpro.core.Position;

public class Enemy implements IntersectionObservable {



    private int hp;
    private int armor;
    private float velocity;

    private Position position;

    public Enemy(int hp, int armor, float velocity) {
        this.hp = hp;
        this.armor = armor;
        this.velocity = velocity;
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
    }
}
