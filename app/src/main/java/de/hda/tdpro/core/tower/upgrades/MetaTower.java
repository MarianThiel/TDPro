package de.hda.tdpro.core.tower.upgrades;

import de.hda.tdpro.core.tower.TowerType;

public class MetaTower {

    private final String name;

    private final int dmg;

    private final int range;

    private final float velocity;

    private final int price;


    public MetaTower(String name, int dmg, int range, float velocity, int price) {
        this.name = name;
        this.dmg = dmg;
        this.range = range;
        this.velocity = velocity;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getDmg() {
        return dmg;
    }


    public int getRange() {
        return range;
    }


    public float getVelocity() {
        return velocity;
    }

    public int getPrice() {
        return price;
    }

    public static MetaTower getMetaTower(TowerType type){
        switch (type){
            case FIRE_TOWER:
                return new MetaTower("Fire Tower",10,150,1f,60);
            case ICE_TOWER:
                return new MetaTower("Ice Tower",10,150,1f,60);
        }
        return new MetaTower("NULL",0,0,0f,0);
    }
}
