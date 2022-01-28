package de.hda.tdpro.core.enemy;

import de.hda.tdpro.core.persistence.ConfigWriter;

public class MetaEnemy {

    private final String name;

    private final int hp;

    private final float vel;

    private final int value;

    public MetaEnemy(String name, int hp, float vel, int value) {
        this.name = name;
        this.hp = hp;
        this.vel = vel;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public float getVel() {
        return vel;
    }

    public int getValue() {
        return value;
    }

    public static MetaEnemy getMetaEnemy(EnemyType type){

        return ConfigWriter.getInstance().readMetaEnemyData(type);
    }
}
