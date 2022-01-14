package de.hda.tdpro.core.enemy;

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
        switch (type){
            case RANDOM:
                return new MetaEnemy("RANDOM",75,30f,20);
            case L1TANK:
                return new MetaEnemy("L1TANK",25,40f,20);
            case L1BOSS:
                return new MetaEnemy("L1BOSS",3500,20f,2000);
        }
        return new MetaEnemy("NULL",0,0f,0);
    }
}
