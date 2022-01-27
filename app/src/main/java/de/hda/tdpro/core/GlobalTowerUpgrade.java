package de.hda.tdpro.core;

public class GlobalTowerUpgrade {

    private String name;

    private String description;

    private int currentLevel;

    private float value;

    private float multi;

    private int max;

    private float base;

    public GlobalTowerUpgrade(String name, String description, int currentLevel, int max, float base, float value, float multi) {
        this.name = name;
        this.description = description;
        this.currentLevel = currentLevel;
        this.value = value;
        this.multi = multi;
        this.base = base;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public float getValue() {
        return value;
    }

    public float getMulti() {
        return multi;
    }

    public int getMax() {
        return max;
    }

    public float getBase() {
        return base;
    }
}
