package de.hda.tdpro.core.tower.upgrades;

public class MetaTower {

    private String name;

    private int dmg;

    private int range;

    private float velocity;


    public MetaTower(String name, int dmg, int range, float velocity) {
        this.name = name;
        this.dmg = dmg;
        this.range = range;
        this.velocity = velocity;
    }

    public String getName() {
        return name;
    }

    public int getDmg() {
        return dmg;
    }

    public void setDmg(int dmg) {
        this.dmg = dmg;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public float getVelocity() {
        return velocity;
    }

    public void setVelocity(float velocity) {
        this.velocity = velocity;
    }
}
