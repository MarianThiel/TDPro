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

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public void setMulti(float multi) {
        this.multi = multi;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void setBase(float base) {
        this.base = base;
    }
}
