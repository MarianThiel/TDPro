package de.hda.tdpro.core.tower.upgrades;

import de.hda.tdpro.ConfigWriter;
import de.hda.tdpro.core.tower.TowerType;

public class MetaTower {

    private final String name;

    private final int dmg;

    private final int range;

    private final float velocity;

    private final int price;

    private final int maxLevel;


    public MetaTower(String name, int dmg, int range, float velocity, int price, int maxLevel) {
        this.name = name;
        this.dmg = dmg;
        this.range = range;
        this.velocity = velocity;
        this.price = price;
        this.maxLevel = maxLevel;
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

    public int getMaxLevel() {
        return maxLevel;
    }

    public static MetaTower getMetaTower(TowerType type){

        return ConfigWriter.getInstance().readMetaTowerData(type);
    }
}
